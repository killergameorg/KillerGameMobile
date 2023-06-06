package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.VideoView;

import DTO.AppState;


public class LoadingActivity extends AppCompatActivity {
    private VideoView videoBackground;
    private ImageButton soundHandler;
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
        soundHandler = findViewById(R.id.muteSoundLoadingActivity);
        soundMusicInitializer();
        if (AppState.getAppState().getIsSoundMusic()){
            soundHandler.setImageResource(R.drawable.ic_baseline_music_note_24);
        }else{
            soundHandler.setImageResource(R.drawable.ic_baseline_music_off_24);
        }
        //Video config
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bg_background_connect);
        videoBackground.setVideoURI(uri);
        videoBackground.start();
        videoBackground.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            mp.setVolume(0,0);
        });

    }

    private void soundMusicInitializer(){
        if (AppState.getAppState().getIsSoundMusic()){
            soundHandler.setImageResource(R.drawable.ic_baseline_music_note_24);
        }else{
            soundHandler.setImageResource(R.drawable.ic_baseline_music_off_24);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart(){
        soundMusicInitializer();
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




    public void soundControl(View view) {
        if (AppState.getAppState().getIsSoundMusic()){
            AppState.getAppState().setIsSoundMusic(false);
            soundHandler.setImageResource(R.drawable.ic_baseline_music_off_24);

        }else{
            AppState.getAppState().setIsSoundMusic(true);
            soundHandler.setImageResource(R.drawable.ic_baseline_music_note_24);
        }

    }

    public void goToJoystick() {
        Intent intent = new Intent(this, ActiveGameActivity.class);
        startActivity(intent);
    }

}