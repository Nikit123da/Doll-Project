package com.example.doll_project;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundMusicService extends Service {
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        player = MediaPlayer.create(this, R.raw.bad_piggies_theme);
        player.setLooping(true);
        player.start();
        player.setVolume(100,100);
        return START_STICKY;
    }

    public void onDestroy(){
        super.onDestroy();
        player.stop();
    }
}
