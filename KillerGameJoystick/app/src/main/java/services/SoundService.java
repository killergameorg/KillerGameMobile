package services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.lisbeth.killergamejoystick.R;

public class SoundService extends Service {
    private MediaPlayer mediaPlayer;
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        public SoundService getService() {
            return SoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.bg_arcade_game);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);
        Log.d("--", "On create");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void play() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.prepareAsync();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
