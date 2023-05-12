package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.textfield.TextInputLayout;

import clients.animation.AnimationController;
import clients.animation.Ball;
import clients.animation.Edge;
import clients.animation.messages.BallMessage;
import automessenger.Messenger;
import clients.asteroids.AsteroidsController;
import communications.AndroidHandler;
import communications.ConnectionController;

public class Connect extends AppCompatActivity {

    static final String TAG = "CCMM";
    static ConnectionController conn;
    static AnimationController animationViewer;
    static Messenger messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        findViewById(R.id.connect).setOnClickListener(view -> {

            String id = ((TextInputLayout)findViewById(R.id.lid)).getEditText().getText().toString();
            //startAutoMessenger(id);
            startAsteroids(id);

        });

    }

    static private void startAnimation(String id) {
        Log.d(TAG, "startAnimation() called with: id = [" + id + "]");
        conn = new ConnectionController("192.168.1."+id, 1234);
        animationViewer = new AnimationController();
        animationViewer.setComm(conn);
        conn.setCommListener(animationViewer);
        conn.initialize();
    }

    static private void startAutoMessenger(String id) {
        Log.d(TAG, "startAutoMessenger() called with: id = [" + id + "]");
        conn = new ConnectionController("192.168.1."+id, 1234);
        messenger = new Messenger();
        messenger.setComm(conn);
        conn.setCommListener(messenger);
        conn.initialize();
    }

    @SuppressWarnings("unused")
    private void startAsteroids(String id) {
        Log.d(TAG, "startAsteroids() called with: id = [" + id + "]");
        ConnectionController conn = new ConnectionController("192.168.1."+id, 1234);
        AsteroidsController asteroids = new AsteroidsController();
        asteroids.setComm(conn);
        conn.setCommListener(asteroids);
        conn.initialize();

        AndroidHandler.conn = conn;
        AndroidHandler.asteroids = asteroids;
        AndroidHandler.shipId = 0;

        Intent intent = new Intent(Connect.this, Controller.class);
        Connect.this.startActivity(intent);
    }

}