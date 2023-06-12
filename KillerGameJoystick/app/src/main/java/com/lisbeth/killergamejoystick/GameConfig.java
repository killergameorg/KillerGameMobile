package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import DTO.AccountInfo;
import clients.asteroids.AsteroidsController;
import clients.asteroids.messages.PackageJoystick;
import communications.AndroidHandler;

public class GameConfig extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AsteroidsController asteroids;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean minusPressed = false;
    private boolean plusPressed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_config);
        this.asteroids = AndroidHandler.asteroids;
        mediaPlayer = MediaPlayer.create(this, R.raw.menu_button_click);

        Button leftButton = findViewById(R.id.button_left);
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    leftPressed = true;
                    sendPackage();
                    // Send package for left button pressed
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    leftPressed = false;
                    sendPackage();
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
                    rightPressed = true;
                    sendPackage();
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    rightPressed = false;
                    sendPackage();
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
                    minusPressed = true;
                    sendPackage();
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    minusPressed = false;
                    sendPackage();
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
                    plusPressed = true;
                    sendPackage();
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    plusPressed = false;
                    sendPackage();
                }
                return false;
            }
        });
    }

    public void goToJoystick() {
        Toast.makeText(this, AccountInfo.getAccount().getTeam().getTeamName().name(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ActiveGameActivity.class);
        startActivity(intent);
    }

    private void sendPackage() {
        TextView buttonStateText = findViewById(R.id.button_state_text);
        String buttonState = "Button State: Left: " + leftPressed + ", Right: " + rightPressed + ", Minus: " + minusPressed + ", Plus: " + plusPressed;
        buttonStateText.setText(buttonState);
        PackageJoystick joystick = new PackageJoystick(leftPressed, rightPressed, minusPressed, plusPressed);
        asteroids.sendShipControlMessage(joystick);
    }

}