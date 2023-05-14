package com.remote.mycontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BIG CONNECT BUTTON ON MAIN SCREEN
        Button connectButton = findViewById(R.id.connect_button);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace this code with package sending and response handling code

                // For now, just start the GameConfigActivity
                Intent intent = new Intent(MainActivity.this, GameConfigActivity.class);
                startActivity(intent);
            }
        });


    }
}