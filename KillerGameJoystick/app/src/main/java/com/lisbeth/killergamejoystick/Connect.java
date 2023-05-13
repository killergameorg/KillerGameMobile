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
    static AnimationController animationViewer;
    static Messenger messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        AndroidHandler.ConnectActivity = this;

        findViewById(R.id.connect).setOnClickListener(view -> {

            String id = ((TextInputLayout)findViewById(R.id.lid)).getEditText().getText().toString();
            startAsteroids(id);

        });

    }

    public void launchActivity(Class c) {
        Intent intent = new Intent(Connect.this, c);
        Connect.this.startActivity(intent);
    }

    static private void startAnimation(String id) {
        Log.d(TAG, "startAnimation() called with: id = [" + id + "]");
        AndroidHandler.conn = new ConnectionController("192.168.1."+id, 1234);
        animationViewer = new AnimationController();
        animationViewer.setComm(AndroidHandler.conn);
        AndroidHandler.conn.setCommListener(animationViewer);
        AndroidHandler.conn.initialize();
    }

    static private void startAutoMessenger(String id) {
        Log.d(TAG, "startAutoMessenger() called with: id = [" + id + "]");
        AndroidHandler.conn = new ConnectionController("192.168.1."+id, 1234);
        messenger = new Messenger();
        messenger.setComm(AndroidHandler.conn);
        AndroidHandler.conn.setCommListener(messenger);
        AndroidHandler.conn.initialize();
    }

    private void startAsteroids(String id) {
        Log.d(TAG, "startAsteroids() called with: id = [" + id + "]");
        AndroidHandler.conn = new ConnectionController("192.168.1."+id, 1234);
        AndroidHandler.asteroids = new AsteroidsController();

        AndroidHandler.asteroids.setComm(AndroidHandler.conn);
        AndroidHandler.conn.setCommListener(AndroidHandler.asteroids);
        AndroidHandler.conn.initialize();
    }

}