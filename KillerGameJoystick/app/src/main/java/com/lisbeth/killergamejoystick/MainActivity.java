package com.lisbeth.killergamejoystick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import automessenger.Messenger;
import communications.ConnectionController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentSecondary = new Intent(MainActivity.this, Connect.class);
        MainActivity.this.startActivity(intentSecondary);

    }

}