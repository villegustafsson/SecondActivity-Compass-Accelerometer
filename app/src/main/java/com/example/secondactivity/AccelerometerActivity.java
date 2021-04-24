package com.example.secondactivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AccelerometerActivity extends Activity {

    private Accelerometer accelerometer;
    private TextView acc_textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer_layout);


        accelerometer = new Accelerometer(this);
        acc_textView = findViewById(R.id.label_acc);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                StringBuilder sb = new StringBuilder();
                sb.append("Accelerometer: \n");
                sb.append("x: " + Math.round(tx) + "\n");
                sb.append("y: " + Math.round(ty) + "\n");
                sb.append("z: " + Math.round(tz) + "\n");
                acc_textView.setText(sb.toString());

                getWindow().getDecorView().setBackgroundColor(Color.rgb(doMath(tx), doMath(ty), doMath(tz)));

            }
        });
    }

    public int doMath(float x){
        int toReturn = Math.round(x);
        return 255-(toReturn)%255;
    }

    @Override
    protected void onResume() {
        super.onResume();

        accelerometer.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        accelerometer.unregister();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
