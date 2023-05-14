package com.remote.mycontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_config);

        Button leftButton = findViewById(R.id.button_left);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send package for left button pressed
            }
        });

        Button rightButton = findViewById(R.id.button_right);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send package for right button pressed
            }
        });

        Button minusButton = findViewById(R.id.button_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send package for minus button pressed
            }
        });

        Button plusButton = findViewById(R.id.button_plus);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send package for plus button pressed
            }
        });

    }
}