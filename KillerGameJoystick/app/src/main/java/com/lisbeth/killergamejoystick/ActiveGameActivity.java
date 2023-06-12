package com.lisbeth.killergamejoystick;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import DTO.AppState;
import clients.asteroids.AsteroidsController;
import clients.asteroids.messages.PackageJoystick;
import communications.AndroidHandler;

public class ActiveGameActivity extends AppCompatActivity {

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean minusPressed = false;
    private boolean plusPressed = false;
    private AsteroidsController asteroids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_game);
        this.asteroids = AndroidHandler.asteroids;

        Button leftButton = findViewById(R.id.button_left);
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    leftPressed = true;
                    sendPackage();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    leftPressed = false;
                    sendPackage();
                }
                return true;
            }
        });

        Button rightButton = findViewById(R.id.button_right);
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    rightPressed = true;
                    sendPackage();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    rightPressed = false;
                    sendPackage();
                }
                return true;
            }
        });

        Button minusButton = findViewById(R.id.button_minus);
        minusButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    minusPressed = true;
                    sendPackage();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    minusPressed = false;
                    sendPackage();
                }
                return true;
            }
        });

        Button plusButton = findViewById(R.id.button_plus);
        plusButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    plusPressed = true;
                    sendPackage();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    plusPressed = false;
                    sendPackage();
                }
                return true;
            }
        });
    }

    public void goToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        asteroids.onConnectionClosed(AppState.getAppState().getIp());
        startActivity(intent);
    }
    private void sendPackage() {
        TextView buttonStateText = findViewById(R.id.button_state_text);
        String buttonState = "Button State: Left: " + leftPressed + ", Right: " + rightPressed + ", Minus: " + minusPressed + ", Plus: " + plusPressed;
        buttonStateText.setText(buttonState);
        PackageJoystick joystick = new PackageJoystick(leftPressed, rightPressed, minusPressed, plusPressed);
        asteroids.sendShipControlMessage(joystick);
    }

    public void goToConnectActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}