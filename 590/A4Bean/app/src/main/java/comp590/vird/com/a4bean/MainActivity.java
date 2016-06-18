package comp590.vird.com.a4bean;

import android.graphics.Color;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.punchthrough.bean.sdk.Bean;
import com.punchthrough.bean.sdk.BeanDiscoveryListener;
import com.punchthrough.bean.sdk.BeanListener;
import com.punchthrough.bean.sdk.BeanManager;
import com.punchthrough.bean.sdk.message.Acceleration;
import com.punchthrough.bean.sdk.message.BeanError;
import com.punchthrough.bean.sdk.message.Callback;
import com.punchthrough.bean.sdk.message.ScratchBank;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    File virDir = new File(Environment.getExternalStorageDirectory(),"A4BeanVir");
    File fout;
    String out = "";
    FileWriter output;
    RadioGroup activity, location;//, person;
    Chronometer clock;
    int[] count = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    Button start, stop, save;
    boolean on = false;
    Bean b;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = (RadioGroup) findViewById(R.id.activity);
        location = (RadioGroup) findViewById(R.id.location);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(activate());
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(activate());
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(save());
        clock = (Chronometer) findViewById(R.id.chronometer);

        virDir.mkdirs();

        start.setEnabled(false);
        stop.setEnabled(false);
        save.setEnabled(false);

        BeanManager.getInstance().startDiscovery(bdl);
    }

    BeanDiscoveryListener bdl = new BeanDiscoveryListener() {
        @Override public void onBeanDiscovered(Bean bean, int rssi) {
            System.out.println("" + bean.getDevice() + ", " + rssi);
            b = bean;
        }

        @Override public void onDiscoveryComplete() {
            b.connect(getApplicationContext(), blsnr);
        }
    };

    BeanListener blsnr = new BeanListener() {
        @Override public void onConnected() {
            start.setEnabled(true);
            stop.setEnabled(false);
            save.setEnabled(false);

            System.out.println("We are connected to: " + b.getDevice().getName());

            b.readAcceleration(new Callback<Acceleration>() {
                @Override public void onResult(Acceleration result) {
                    System.out.println("Connect: " + result.x() + ", " + result.y() + ", " + result.z());
                }
            });
        }

        @Override public void onConnectionFailed() {
            System.out.println("connection failed");
        }

        @Override public void onDisconnected() {
            System.out.println("disconnected");
        }

        @Override public void onSerialMessageReceived(byte[] data) {
            findViewById(R.id.mainLayout).setBackgroundColor(Color.GREEN);
            if(on){
                String dt = new String(data);
                if(dt.length() > 1) {
                    String[] values = dt.split("\\s+");
                    double x = (Double.parseDouble(values[0])/(2^10))*2;
                    double y = (Double.parseDouble(values[1])/(2^10))*2;
                    double z = (Double.parseDouble(values[2])/(2^10))*2;
                    out += System.currentTimeMillis() + ", " + x + ", " + y + ", " + z + "\r\n";
                }
            }
        }

        @Override public void onScratchValueChanged(ScratchBank bank, byte[] value) {
            System.out.println("Scratch value changed");
        }

        @Override public void onError(BeanError error) {
            System.err.println(error.toString());
        }
    };

    private View.OnClickListener activate(){
        return new View.OnClickListener() {
            @Override public void onClick(View v) {
                if(v.getId() == R.id.start) {
                    start.setEnabled(false);
                    stop.setEnabled(true);
                    save.setEnabled(false);
                    int ai = activity.indexOfChild(findViewById(activity.getCheckedRadioButtonId()));
                    int li = location.indexOfChild(findViewById(location.getCheckedRadioButtonId()));
                    int serial = (li == 0 && ai == 0) ? 0 : (li+1)*(ai+1);
                    String fileName = ((RadioButton)activity.getChildAt(ai)).getText().toString().toUpperCase() + "_" +
                            ((RadioButton)location.getChildAt(li)).getText().toString().toUpperCase() + "_" + count[serial] + ".txt";
                    count[serial]++;

                    fout = new File(virDir, fileName);

                    out = "";
                    on = true;
                    clock.setBase(SystemClock.elapsedRealtime());
                    clock.start();
                }else {
                    start.setEnabled(true);
                    stop.setEnabled(false);
                    save.setEnabled(true);
                    on = false;
                    clock.stop();
                }
            }
        };
    }

    private View.OnClickListener save(){
        start.setEnabled(true);
        stop.setEnabled(false);
        save.setEnabled(false);
        return new View.OnClickListener() {
            @Override public void onClick(View v) {
                try {
                    fout.createNewFile();
                    output = new FileWriter(fout);
                    output.append(out);
                    output.flush();
                    output.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
    }

    @Override protected void onStop() {
        super.onStop();
        try { output.close(); }catch(Exception e){ e.printStackTrace(); }
        if(on)
            fout.delete();
    }
}
