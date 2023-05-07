package com.lisbeth.killergamejoystick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private VideoView videoBackground;
    private boolean isSound;
    private ImageButton soundHandler;
    Drawable musicOn;
    Drawable musicOff;

    private MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        videoBackground = findViewById(R.id.backgroundVideo);
        music = MediaPlayer.create(MainActivity.this, R.raw.bg_arcade_game);
        Resources res = getResources();
        musicOn = ResourcesCompat.getDrawable(res, R.drawable.ic_baseline_music_note_24, null);
        musicOff = ResourcesCompat.getDrawable(res, R.drawable.ic_baseline_music_off_24, null);
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
        music.start();
        isSound = true;
        music.setVolume(100, 100);
        music.setLooping(true);
    }

    @Override
    protected void onPostResume(){
        videoBackground.resume();
        if (isSound){
        music.start();
        }
        super.onPostResume();
    }

    @Override
    protected void onResume(){
        if (isSound){
        music.start();
        }
        videoBackground.start();
        super.onResume();
    }
    @Override
    protected void onRestart(){
        videoBackground.start();
        if (isSound){
        music.start();
        }
        super.onRestart();
    }
    @Override
    protected void onPause(){
        videoBackground.suspend();
        music.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        videoBackground.stopPlayback();
        music.stop();
        super.onDestroy();
    }

    public void soundControl(View view) {
        if (isSound){
            music.pause();
            isSound = false;
            soundHandler.setImageResource(R.drawable.ic_baseline_music_note_24);

        }else{
            music.start();
            isSound = true;
            soundHandler.setImageResource(R.drawable.ic_baseline_music_off_24);
        }

    }
}