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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;
import DTO.AppState;
import clients.asteroids.AsteroidsController;
import communications.AndroidHandler;
import communications.ConnectionController;
import controllers.SoundManager;


import android.content.Context;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private VideoView videoBackground;
    private ImageButton soundHandler;
    private Button connectButton;
    private SoundManager soundManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        soundManager = new SoundManager(this, R.raw.bg_arcade_game);

        soundHandler = findViewById(R.id.muteSound);
        AndroidHandler.ConnectActivity = this;
        soundMusicInitializer();
        videoBackground = findViewById(R.id.backgroundVideo);

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
    protected void onStart() {
        super.onStart();

        soundManager.playSound();
        soundManager.startMusic();
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
        videoBackground.start();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        soundMusicInitializer();
    }
    @Override
    protected void onPause(){
        videoBackground.suspend();
        soundManager.pauseSound();
        soundManager.pauseMusic();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        videoBackground.stopPlayback();
        soundManager.stopSound();
        soundManager.stopMusic();
        soundManager.release();
        super.onDestroy();
    }

    public void soundControl(View view) {
        if (AppState.getAppState().getIsSoundMusic()){
            soundHandler.setImageResource(R.drawable.ic_baseline_music_off_24);
            AppState.getAppState().setIsSoundMusic(false);
            soundManager.pauseSound();
            soundManager.pauseMusic();
        }else{
            AppState.getAppState().setIsSoundMusic(true);
            soundHandler.setImageResource(R.drawable.ic_baseline_music_note_24);
            AppState.getAppState().setIsSoundMusic(true);
            soundManager.startMusic();
        }

    }

    public void tryToConnect(View view) {
        startAsteroids();

    }



    private void startAsteroids() {
        Random ran = new Random();
        AppState.getAppState().setIp("192.168.1."+ran.nextInt(13)+1);
        AndroidHandler.conn = new ConnectionController(AppState.getAppState().getIp(), 1234);
        AndroidHandler.asteroids = new AsteroidsController();
        AndroidHandler.asteroids.setComm(AndroidHandler.conn);
        AndroidHandler.conn.setCommListener(AndroidHandler.asteroids);
        AndroidHandler.conn.initialize();
    }

    public void goToConfigActivity(){
        Intent intent = new Intent(MainActivity.this, GameConfig.class);
        startActivity(intent);
    }

    public void goToLoadingActivity(){
        Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
        startActivity(intent);
    }

}