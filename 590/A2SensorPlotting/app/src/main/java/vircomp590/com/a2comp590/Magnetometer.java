package vircomp590.com.a2comp590;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Magnetometer extends AppCompatActivity implements SensorEventListener {

    GraphView gv;
    private SensorManager sm;
    private Sensor s;
    int time = 0;
    long t;
    LineGraphSeries<DataPoint> value, mean, stdev;
    double[] mean5 = new double[5], std5 = new double[5];
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetometer);

        gv = (GraphView) findViewById(R.id.maggraph);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        value = new LineGraphSeries<>();
        value.setColor(Color.BLUE);
        mean = new LineGraphSeries<>();
        mean.setColor(Color.GREEN);
        stdev = new LineGraphSeries<>();
        stdev.setColor(Color.RED);
        gv.addSeries(value);
        value.setTitle("Value");
        gv.addSeries(mean);
        mean.setTitle("Mean");
        gv.addSeries(stdev);
        stdev.setTitle("Std Dev");
        gv.getLegendRenderer().setVisible(true);
        gv.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        img = (ImageView) findViewById(R.id.animatedMag);
//        img.setImageBitmap(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(s != null)
            sm.registerListener(this, s, sm.SENSOR_DELAY_UI);
        t = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(t+100 < System.currentTimeMillis()) {
            double mag = Math.sqrt((double) (event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]));
            double temp = 0;
            for(double d: mean5) {
                temp += d;
            }
            double avgMean = temp/5;
            std5[time%5] = Math.pow(mag-avgMean,2);
            temp = 0;
            for(double d: std5){
                temp += d;
            }
            double avgStd = temp/5;
            if(avgStd < 2000)
                img.setLayoutParams(new LinearLayout.LayoutParams(200,200));
            else if(avgStd < 100000)
                img.setLayoutParams(new LinearLayout.LayoutParams(300,300));
            else if(avgStd < 500000)
                img.setLayoutParams(new LinearLayout.LayoutParams(400,400));
            else if(avgStd < 1000000)
                img.setLayoutParams(new LinearLayout.LayoutParams(500,500));
            else
                img.setLayoutParams(new LinearLayout.LayoutParams(600,600));
            img.invalidate();
            value.appendData(new DataPoint(time, mag), false, 50);
            mean.appendData(new DataPoint(time, avgMean), false, 50);
            stdev.appendData(new DataPoint(time, avgStd), false, 50);
            time++;
            t = System.currentTimeMillis();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
