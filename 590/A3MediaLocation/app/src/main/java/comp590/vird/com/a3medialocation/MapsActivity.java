package comp590.vird.com.a3medialocation;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleMap mMap;
    TextView latitude, longitude, address, name;
    GoogleApiClient c = null;
    int lastLoc = 5;
    MediaPlayer mp;

    public static final double SITTERSON_LATITUDE = 35.909567; // +/-.00028
    public static final double SITTERSON_LONGITUDE = -79.053044; // +/-.00028
    public static final double POLK_PLACE_LATITUDE = 35.910858; // +/-.001
    public static final double POLK_PLACE_LONGITUDE = -79.050563; // +/-.001
    public static final double OLD_WELL_LATITUDE = 35.912057; // +/-.00017
    public static final double OLD_WELL_LONGITUDE = -79.051240; // +/-.00017

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latitude = (TextView) findViewById(R.id.latitude);
        longitude = (TextView) findViewById(R.id.longitude);
        address = (TextView) findViewById(R.id.address);
        name = (TextView) findViewById(R.id.name);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().equals("")){
                    name.setText("Joel");
                    ((LinearLayout) findViewById(R.id.layout)).setBackgroundColor(Color.GREEN);
                }else{
                    name.setText("");
                    ((LinearLayout) findViewById(R.id.layout)).setBackgroundColor(Color.WHITE);
                }
            }
        });

        mp = new MediaPlayer();
        mp.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        c = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }

    @Override public void onMapReady(GoogleMap googleMap) { mMap = googleMap; }

    @Override protected void onStart() {
        c.connect();
        super.onStart();
    }

    @Override protected void onStop() {
        c.disconnect();
        if(mp != null && mp.isPlaying())
            mp.stop();
        super.onStop();
    }

    @Override public void onConnected(@Nullable Bundle bundle) {
        try {
            Location loc = LocationServices.FusedLocationApi.getLastLocation(c);
            latitude.setText(getString(R.string.latitude) + loc.getLatitude());
            longitude.setText(getString(R.string.longitude) + loc.getLongitude());
            LatLng ll = new LatLng(loc.getLatitude(), loc.getLongitude());
            CameraPosition cameraPosition = new CameraPosition.Builder().target(ll).zoom(17).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(500);
            mLocationRequest.setFastestInterval(250);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationServices.FusedLocationApi.requestLocationUpdates(c, mLocationRequest, this);
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    @Override public void onConnectionSuspended(int i) {}

    @Override public void onLocationChanged(Location location) {
        latitude.setText(getString(R.string.latitude) + location.getLatitude());
        longitude.setText(getString(R.string.longitude) + location.getLongitude());
        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(ll).zoom(17).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        checkLocations(location);

        Geocoder g = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> la = g.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            ArrayList<String> add = new ArrayList<>();
            add.add("Address: ");
            for(int i = 0; i < la.get(0).getMaxAddressLineIndex(); i++)
                add.add(la.get(0).getAddressLine(i));
            address.setText(TextUtils.join(System.getProperty("line.separator"),add));
        }catch (Exception ex) { System.err.println(ex.getLocalizedMessage()); }

    }

    @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { System.err.println("Wrong... " + connectionResult.getErrorMessage()); }

    private void checkLocations(Location location){
        if(Math.abs(location.getLongitude()-SITTERSON_LONGITUDE) < 0.00028 && Math.abs(location.getLatitude()-SITTERSON_LATITUDE) < 0.00028){
            if(lastLoc != 0) {
                marker(SITTERSON_LATITUDE,SITTERSON_LONGITUDE);
                if(mp.isPlaying())
                    mp.stop();
                mp = MediaPlayer.create(getApplicationContext(), R.raw.coolkids);
                mp.start();
                lastLoc = 0;
            }
        }else if(Math.abs(location.getLongitude()-POLK_PLACE_LONGITUDE) < 0.0008 && Math.abs(location.getLatitude()-POLK_PLACE_LATITUDE) < 0.0008){
            if(lastLoc != 1) {
                marker(POLK_PLACE_LATITUDE,POLK_PLACE_LONGITUDE);
                if(mp.isPlaying())
                    mp.stop();
                mp = MediaPlayer.create(getApplicationContext(), R.raw.sunshine);
                mp.start();
                lastLoc = 1;
            }
        }else if(Math.abs(location.getLongitude()-OLD_WELL_LONGITUDE) < 0.00017 && Math.abs(location.getLatitude()-OLD_WELL_LATITUDE) < 0.00017){
            if(lastLoc != 2) {
                marker(OLD_WELL_LATITUDE,OLD_WELL_LONGITUDE);
                if(mp.isPlaying())
                    mp.stop();
                mp = MediaPlayer.create(getApplicationContext(), R.raw.sugar);
                mp.start();
                lastLoc = 2;
            }
        }else{
            if(mp.isPlaying())
                mp.stop();
            lastLoc = 5;
            mMap.clear();
        }
    }

    private void marker(final double latitude, final double longitude) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
    }
}
