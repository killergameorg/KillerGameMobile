package com.lisbeth.killergamejoystick;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import automessenger.Messenger;
import communications.ConnectionController;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "CCMM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.connect).setOnClickListener(view -> {

            String id = ((TextInputLayout)findViewById(R.id.lid)).getEditText().getText().toString();
            startAutoMessenger(id);

        });

    }

    static private void startAutoMessenger(String id) {
        Log.d(TAG, "startAutoMessenger() called with: id = [" + id + "]");
        ConnectionController conn = new ConnectionController("192.168.1."+id, 1234);
        Messenger messenger = new Messenger();
        messenger.setComm(conn);
        conn.setCommListener(messenger);
        conn.initialize();
    }

}