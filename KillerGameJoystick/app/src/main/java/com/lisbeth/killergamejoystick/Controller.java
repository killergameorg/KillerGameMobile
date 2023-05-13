package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import clients.asteroids.AsteroidsController;
import clients.asteroids.messages.AccelerateShipMessage;
import clients.asteroids.messages.RotateShipMessage;
import communications.AndroidHandler;
import communications.ConnectionController;

public class Controller extends AppCompatActivity {

    final String TAG = this.getClass().getName();

    private ConnectionController conn;
    private AsteroidsController asteroids;

    private boolean accelerate = false;
    private int rotation = 0;
    private boolean right = false;
    private boolean left = false;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        AndroidHandler.ControllerActivity = this;

        this.conn = AndroidHandler.conn;
        this.asteroids = AndroidHandler.asteroids;

        Button bGas = findViewById(R.id.gas);
        bGas.setText("ID: " + AndroidHandler.shipId);

        Button bLeft = findViewById(R.id.left);
        Button bRight = findViewById(R.id.right);

        bGas.setOnTouchListener((View.OnTouchListener) (v, motionEvent) -> {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_UP) {
                AccelerateShipMessage msg = new AccelerateShipMessage();
                accelerate = !accelerate;
                msg.shipId = AndroidHandler.shipId;
                msg.accelerate = accelerate;

                Log.d(TAG, "bGas.setOnTouchListener() returned: shipId = [ " + AndroidHandler.shipId + " ], accelerate = [ " + msg.accelerate + " ], MotionEvent = [ " + motionEvent.getAction() + " ]");

                asteroids.sendShipControlMessage(msg);
            }

            return false;
        });

        bLeft.setOnTouchListener((view, motionEvent) -> {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_UP) {
                left = !left;

                rotation = left ? -1 : right ? 1 : 0;

                RotateShipMessage msg = new RotateShipMessage();
                msg.shipId = AndroidHandler.shipId;
                msg.rotation = rotation;

                Log.d(TAG, "bLeft.setOnTouchListener() returned: shipId = [ " + msg.shipId + " ], rotation = [ " + msg.rotation + " ], MotionEvent = [ " + motionEvent.getAction() + " ]");

                asteroids.sendShipControlMessage(msg);
            }

            return false;
        });

        bRight.setOnTouchListener((view, motionEvent) -> {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_UP) {
                right = !right;

                rotation = right ? 1 : left ? -1 : 0;

                RotateShipMessage msg = new RotateShipMessage();
                msg.shipId = AndroidHandler.shipId;
                msg.rotation = rotation;

                Log.d(TAG, "bRight.setOnTouchListener() returned: shipId = [ " + msg.shipId + " ], rotation = [ " + msg.rotation + " ], MotionEvent = [ " + motionEvent.getAction() + " ]");

                asteroids.sendShipControlMessage(msg);
            }

            return false;
        });

    }
}