package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        this.conn = AndroidHandler.conn;
        this.asteroids = AndroidHandler.asteroids;

        Button gas = findViewById(R.id.gas);
        Button left = findViewById(R.id.left);
        Button right = findViewById(R.id.right);

        gas.setOnClickListener(view1 -> {

            this.accelerate = !accelerate;
            AccelerateShipMessage msg = new AccelerateShipMessage();
            msg.shipId = AndroidHandler.shipId;
            msg.accelerate = this.accelerate;

            Log.d(TAG, "onCreate() returned: shipId = [ " + AndroidHandler.shipId + " ], accelerate = [ " + this.accelerate + " ]");

            asteroids.sendShipControlMessage(msg);

        });

        left.setOnClickListener(view1 -> {

            this.rotation = this.rotation == -1 ? 0 : -1;
            RotateShipMessage msg = new RotateShipMessage();
            msg.shipId = AndroidHandler.shipId;
            msg.rotation = this.rotation;

            Log.d(TAG, "onCreate() returned: shipId = [ " + AndroidHandler.shipId + " ], rotation = [ " + this.rotation + " ]");

            asteroids.sendShipControlMessage(msg);

        });

        right.setOnClickListener(view1 -> {

            this.rotation = this.rotation == 1 ? 0 : 1;
            RotateShipMessage msg = new RotateShipMessage();
            msg.shipId = AndroidHandler.shipId;
            msg.rotation = this.rotation;

            Log.d(TAG, "onCreate() returned: shipId = [ " + AndroidHandler.shipId + " ], rotation = [ " + this.rotation + " ]");

            asteroids.sendShipControlMessage(msg);

        });

    }
}