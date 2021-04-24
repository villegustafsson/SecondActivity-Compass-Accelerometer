package com.example.secondactivity;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CompassActivity extends Activity implements SensorEventListener {

    // Angle of the picture
    private float degreeStart = 0f;

    private TextView degreeLabel;
    private TextView directionLabel;

    // Define the compass image
    private ImageView compassImage;

    private SensorManager sensorManagerCompass;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass_layout);

        degreeLabel = findViewById(R.id.headingLabel);
        directionLabel = findViewById(R.id.directionLabel);
        compassImage = findViewById(R.id.imageView);

        sensorManagerCompass = (SensorManager) getSystemService(SENSOR_SERVICE);

        getWindow().getDecorView().setBackgroundColor(Color.GREEN);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float degree = Math.round(sensorEvent.values[0]*100*5/2 * 1.0169491525 - 180);

        // Rotation animation
        RotateAnimation rotateAnimation = new RotateAnimation(
                degreeStart,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        //degree = Math.abs(degree);
        degree = degree%360;

        degreeLabel.setText("Heading: " + Float.toString(degree) + " degrees");
        float testDegree = Math.abs(degree);
        if((testDegree >0 && testDegree <45) || (testDegree >315 && testDegree <360) ){
            directionLabel.setText("North");
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);

        } else if(45 <testDegree&& testDegree <135){
            directionLabel.setText("East");
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        } else if(135 < testDegree && testDegree < 225){
            directionLabel.setText("South");
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        } else if(225 < testDegree && testDegree < 315){
            directionLabel.setText("West");
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        }


        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(210);

        compassImage.startAnimation(rotateAnimation);
        degreeStart = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManagerCompass.registerListener(this, sensorManagerCompass.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManagerCompass.unregisterListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
