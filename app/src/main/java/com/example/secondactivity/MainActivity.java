package com.example.secondactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button_greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button_greeting = findViewById(R.id.showGreetings);


    }
    // Kallas på från activity_main
    public void showGreetings(View view){
        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Open Accelerometer")){
            Intent intent = new Intent(this, AccelerometerActivity.class);
            startActivity(intent);
        } else if(button_text.equals("Open Compass")){
            Intent intent = new Intent(this, CompassActivity.class);
            startActivity(intent);
        } else if(button_text.equals("Open Shaker")){
            Intent intent = new Intent(this, ShakeActivity.class);
            startActivity(intent);
        }
    }


}