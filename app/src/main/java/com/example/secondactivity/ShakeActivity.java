package com.example.secondactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ShakeActivity extends Activity {

    private Accelerometer accelerometer;
    private float counter = 0f;
    private ImageView polaroidPicture;
    private TextView counterLabel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_layout);

        polaroidPicture = findViewById(R.id.polaroidPicture);
        polaroidPicture.setVisibility(View.INVISIBLE);

        counterLabel = findViewById(R.id.shakeLabel2);

        accelerometer = new Accelerometer(this);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {

                counter += Math.round(Math.abs(tx)+Math.abs(ty)+Math.abs(tz));
                if(counter > 250){
                    polaroidPicture.setVisibility(View.VISIBLE);
                    counterLabel.setText("Shake it like a polaroid picture!");
                    accelerometer.unregister();
                } else{
                    counterLabel.setText("Counter: " + counter);
                }
            }
        });


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
