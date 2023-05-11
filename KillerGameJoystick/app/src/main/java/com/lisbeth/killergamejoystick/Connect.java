package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.textfield.TextInputLayout;

import animation.AnimationController;
import animation.Ball;
import animation.Edge;
import animation.messages.BallMessage;
import automessenger.Messenger;
import communications.ConnectionController;

public class Connect extends AppCompatActivity {

    static final String TAG = "CCMM";
    static ConnectionController conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        findViewById(R.id.connect).setOnClickListener(view -> {

            String id = ((TextInputLayout)findViewById(R.id.lid)).getEditText().getText().toString();
            //startAutoMessenger(id);
            startAnimation(id);

        });

        findViewById(R.id.activar).setOnClickListener(view -> {

            BallMessage bm = new BallMessage();
            bm.from = Edge.DOWN;
            bm.ball = new Ball(0,0,1,1,10);
            conn.sendPrivate(conn.getServerIp(), bm);

        });

    }

    static private void startAnimation(String id) {
        ConnectionController conn = new ConnectionController("192.168.1."+id, 1234);
        AnimationController animationViewer = new AnimationController();
        animationViewer.setComm(conn);
        conn.setCommListener(animationViewer);
        conn.initialize();
    }

    static private void startAutoMessenger(String id) {
        Log.d(TAG, "startAutoMessenger() called with: id = [" + id + "]");
        conn = new ConnectionController("192.168.1."+id, 1234);
        Messenger messenger = new Messenger();
        messenger.setComm(conn);
        conn.setCommListener(messenger);
        conn.initialize();
    }
}