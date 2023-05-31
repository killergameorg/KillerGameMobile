package com.lisbeth.killergamejoystick;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ActiveGameActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_active_game);

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

    private void sendPackage() {
        //TEST BUTTON FUNCTIONALITY
        // Update the button state text view
        TextView buttonStateText = findViewById(R.id.button_state_text);
        String buttonState = "Button State: Left: " + leftPressed + ", Right: " + rightPressed + ", Minus: " + minusPressed + ", Plus: " + plusPressed;
        buttonStateText.setText(buttonState);

        // TODO: Send the package containing the controller object with the current button states
        // Implement the logic to send the package here or call a separate method to handle it.
        // Replace the following comment with code to send the package:
        // sendPackage(controller);
    }
}