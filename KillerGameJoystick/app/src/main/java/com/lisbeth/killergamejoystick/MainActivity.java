package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.VideoView;
import DTO.AppState;
import controllers.SoundController;
import services.SoundService;


import android.content.Context;

public class MainActivity extends AppCompatActivity {
    private VideoView videoBackground;

    private ImageButton soundHandler;



    @Override
    protected void onStart() {
        super.onStart();
        SoundController.play();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // Sound implementation
        SoundController.bindService(this);
        videoBackground = findViewById(R.id.backgroundVideo);
        soundHandler = findViewById(R.id.muteSound);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bg_background_connect);
        videoBackground.setVideoURI(uri);
        videoBackground.start();
        videoBackground.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0,0);
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (AppState.getAppState().getIsSoundMusic()) {
            SoundController.stop();
            AppState.getAppState().setIsSoundMusic(false);
        }
    }
    /*
        @Override
        protected void onPostResume(){
            videoBackground.resume();
            if (AppState.getAppState().getIsSoundMusic()){
                mediaPlayerService.play();
            }
            super.onPostResume();
        }

        @Override
        protected void onResume(){
            if (AppState.getAppState().getIsSoundMusic()){
                mediaPlayerService.play();
            }
            videoBackground.start();
            super.onResume();
        }
      */
    @Override
    protected void onRestart(){
        if (AppState.getAppState().getIsSoundMusic()){
            SoundController.play();
        }
        videoBackground.start();
        super.onRestart();
    }
    @Override
    protected void onPause(){
        videoBackground.suspend();
        SoundController.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        videoBackground.stopPlayback();
        SoundController.unbindService(this);
        //stopService(SoundService.class);
        super.onDestroy();
    }

    public void soundControl(View view) {
        if (AppState.getAppState().getIsSoundMusic()){
            SoundController.pause();
            AppState.getAppState().setIsSoundMusic(false);
            soundHandler.setImageResource(R.drawable.ic_baseline_music_off_24);

        }else{
            SoundController.play();
            AppState.getAppState().setIsSoundMusic(true);
            soundHandler.setImageResource(R.drawable.ic_baseline_music_note_24);
        }

    }

    public void tryToConnect(View view) {

        Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
        startActivity(intent);
    }
}