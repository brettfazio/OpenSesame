package com.example.juleeyahwright.opensesame;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private static final float[] NEGATIVE = {
            -1.0f,     0,     0,    0, 255, // red
            0, -1.0f,     0,    0, 255, // green
            0,     0, -1.0f,    0, 255, // blue
            0,     0,     0, 1.0f,   0  // alpha
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

//    protected void invertColors() {
//        setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
//    }

}
