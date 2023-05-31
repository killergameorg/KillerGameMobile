package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GameConfig extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_config);

        mediaPlayer = MediaPlayer.create(this, R.raw.menu_button_click);

        Button leftButton = findViewById(R.id.button_left);
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    // Send package for left button pressed
                }
                return false;
            }
        });

        Button rightButton = findViewById(R.id.button_right);
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    // Send package for right button pressed
                }
                return false;
            }
        });

        Button minusButton = findViewById(R.id.button_minus);
        minusButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    // Send package for minus button pressed
                }
                return false;
            }
        });

        Button plusButton = findViewById(R.id.button_plus);
        plusButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    // Send package for plus button pressed
                }
                return false;
            }
        });
    }

}