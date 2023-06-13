package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import DTO.AccountInfo;
import DTO.AppState;


public class LoadingActivity extends AppCompatActivity {
    private VideoView videoBackground;
    private boolean isServiceBound;
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);
        videoBackground = findViewById(R.id.backgroundVideoLoadingActivity);
        //Video config
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bg_background_connect);
        videoBackground.setVideoURI(uri);
        videoBackground.start();
        videoBackground.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            mp.setVolume(0,0);
        });

    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart(){
        videoBackground.start();
        super.onRestart();
    }

    @Override
    protected void onPause(){
        videoBackground.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        videoBackground.stopPlayback();
        super.onDestroy();
    }






    public void goToJoystick() {
        Toast.makeText(this, AccountInfo.getAccount().getTeam().getTeamName().name(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ActiveGameActivity.class);
        startActivity(intent);

    }

}