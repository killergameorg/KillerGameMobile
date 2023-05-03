package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lisbeth.killergamejoystick.Comunnications.clients.automessenger.Messenger;
import com.lisbeth.killergamejoystick.Comunnications.communications.ConnectionController;
import com.lisbeth.killergamejoystick.Comunnications.main.Main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAutoMessenger();

    }

    @SuppressWarnings("unused")
    static private void startAutoMessenger() {
        ConnectionController conn = new ConnectionController();
        Messenger messenger = new Messenger();
        messenger.setComm(conn);
        conn.setCommListener(messenger);
        conn.initialize();
    }

}