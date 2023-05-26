package controllers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import services.SoundService;

public class SoundController {
    private static SoundService service;
    private static boolean isServiceBound = false;

    public static void bindService(Context context) {
        if (!isServiceBound) {
            Intent intent = new Intent(context, SoundService.class);
            context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            isServiceBound = true;
        }
    }

    public static void unbindService(Context context) {
        if (isServiceBound) {
            context.unbindService(serviceConnection);
            isServiceBound = false;
        }
    }

    public static void play() {
        if (service != null) {
            service.play();
        }
    }

    public static void pause() {
        if (service != null) {
            service.pause();
        }
    }

    public static void stop() {
        if (service != null) {
            service.stop();
        }
    }

    private static ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SoundService.LocalBinder binder = (SoundService.LocalBinder) iBinder;
            service = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            service = null;
        }
    };
}
