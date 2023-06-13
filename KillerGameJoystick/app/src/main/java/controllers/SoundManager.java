package controllers;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;

public class SoundManager {
    private static final int MAX_STREAMS = 5;
    private static final int DEFAULT_VOLUME = 1;
    private static final int DEFAULT_PRIORITY = 1;
    private static final int DEFAULT_LOOP = 0;
    private static final int DEFAULT_RATE = 1;

    private SoundPool soundPool;
    private int soundId;
    private boolean isSoundLoaded;
    private MediaPlayer mediaPlayer;

    public SoundManager(Context context, int soundResourceId) {
        // Initialize SoundPool
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(MAX_STREAMS)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // Load sound into SoundPool
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                isSoundLoaded = true;
            }
        });
        soundId = soundPool.load(context, soundResourceId, DEFAULT_PRIORITY);

        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(context, soundResourceId);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(DEFAULT_VOLUME, DEFAULT_VOLUME);
    }

    public void playSound() {
        if (isSoundLoaded) {
            soundPool.play(soundId, DEFAULT_VOLUME, DEFAULT_VOLUME, DEFAULT_PRIORITY, DEFAULT_LOOP, DEFAULT_RATE);
        }
    }

    public void pauseSound() {
        if (isSoundLoaded) {
            soundPool.autoPause();
        }
    }

    public void resumeSound() {
        if (isSoundLoaded) {
            soundPool.autoResume();
        }
    }

    public void stopSound() {
        if (isSoundLoaded) {
            soundPool.stop(soundId);
        }
    }

    public void startMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stopMusic() {
        mediaPlayer.stop();
        mediaPlayer.prepareAsync();
    }

    public void release() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
