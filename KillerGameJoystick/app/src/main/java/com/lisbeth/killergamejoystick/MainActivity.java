package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lisbeth.killergamejoystick.Comunnications.clients.automessenger.Messenger;
import com.lisbeth.killergamejoystick.Comunnications.communications.ConnectionController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAutoMessenger();

    }

    static private void startAutoMessenger() {
        ConnectionController conn = new ConnectionController("192.168.95.123", 1234);
        Messenger messenger = new Messenger();
        messenger.setComm(conn);
        conn.setCommListener(messenger);
        conn.initialize();
    }

}