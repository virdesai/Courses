package vircomp590.com.a2comp590;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SensorChooser extends AppCompatActivity {

    String[] sensors = {"Accelerometer","Light","Gyroscope","Magnetometer"};
    int height, width;
    SensorManager sm;
    LinearLayout sensorLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        setContentView(R.layout.activity_sensor_chooser);

        sensorLayout = (LinearLayout) findViewById(R.id.sensor_layout);

        height = dm.heightPixels;
        width = dm.widthPixels;

        context = this;

    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout.LayoutParams lb = new LinearLayout.LayoutParams(2*(width/5), LinearLayout.LayoutParams.MATCH_PARENT);
        lb.setMargins(5,0,0,0);
        Button accelButton = (Button) findViewById(R.id.accelerometer);
        accelButton.setLayoutParams(lb);
        accelButton.setText("Accelerometer");
        accelButton.setTextSize(14);
        accelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Accelerometer.class);
                startActivity(i);
            }
        });
        findViewById(R.id.alayout).setLayoutParams(new LinearLayout.LayoutParams(3*(width/5), LinearLayout.LayoutParams.MATCH_PARENT));
        Button lightButton = (Button) findViewById(R.id.light);
        lightButton.setLayoutParams(lb);
        lightButton.setText("Light Sensor");
        lightButton.setTextSize(14);
        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Light.class);
                startActivity(i);
            }
        });
        findViewById(R.id.llayout).setLayoutParams(new LinearLayout.LayoutParams(3*(width/5), LinearLayout.LayoutParams.MATCH_PARENT));
        Button gyroButton = (Button) findViewById(R.id.gyroscope);
        gyroButton.setLayoutParams(lb);
        gyroButton.setText("Gyroscope");
        gyroButton.setTextSize(14);
        gyroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Gyroscope.class);
                startActivity(i);
            }
        });
        findViewById(R.id.glayout).setLayoutParams(new LinearLayout.LayoutParams(3*(width/5), LinearLayout.LayoutParams.MATCH_PARENT));
        Button magButton = (Button) findViewById(R.id.magnetometer);
        magButton.setLayoutParams(lb);
        magButton.setText("Magnetometer");
        magButton.setTextSize(14);
        magButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Magnetometer.class);
                startActivity(i);
            }
        });
        findViewById(R.id.mlayout).setLayoutParams(new LinearLayout.LayoutParams(3*(width/5), LinearLayout.LayoutParams.MATCH_PARENT));
        status();
    }

    private void status(){
        Sensor[] sensors = {sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), sm.getDefaultSensor(Sensor.TYPE_LIGHT),
        sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE), sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)};
        TextView[] textViews = {(TextView) findViewById(R.id.astatus), (TextView) findViewById(R.id.ainfo), (TextView) findViewById(R.id.lstatus),
                (TextView) findViewById(R.id.linfo), (TextView) findViewById(R.id.gstatus), (TextView) findViewById(R.id.ginfo),
                (TextView) findViewById(R.id.mstatus), (TextView) findViewById(R.id.minfo)};
        textViews[0].setText((getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER)) ? "Status: " + sensors[0].getName() + " present" : "Status: Accelerometer not Present");
        textViews[1].setText("Info: maxRng=" + sensors[0].getMaximumRange() + ", res=" + sensors[0].getResolution() + ", power=" + sensors[0].getPower() + ", minDelay=" + sensors[0].getMinDelay());
        textViews[2].setText((getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT)) ? "Status: " + sensors[1].getName() + " present" : "Status: Light Sensor not Present");
        textViews[3].setText("Info: maxRng=" + sensors[1].getMaximumRange() + ", res=" + sensors[1].getResolution() + ", power=" + sensors[1].getPower() + ", minDelay=" + sensors[1].getMinDelay());
        textViews[4].setText((getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE)) ? "Status: " + sensors[2].getName() + " present" : "Status: Gyroscope not Present");
        textViews[5].setText("Info: maxRng=" + sensors[2].getMaximumRange() + ", res=" + sensors[2].getResolution() + ", power=" + sensors[2].getPower() + ", minDelay=" + sensors[2].getMinDelay());
        textViews[6].setText((getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)) ? "Status: " + sensors[3].getName() + " present" : "Status: Magnetometer not Present");
        textViews[7].setText("Info: maxRng=" + sensors[3].getMaximumRange() + ", res=" + sensors[3].getResolution() + ", power=" + sensors[3].getPower() + ", minDelay=" + sensors[3].getMinDelay());
    }
}
