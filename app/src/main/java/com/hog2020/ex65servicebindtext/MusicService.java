package com.hog2020.ex65servicebindtext;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MusicService extends Service {


    public MediaPlayer mp;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends Binder {
        public MusicService getServiceObject(){
            return MusicService.this;
        }
    }

    public void startMusic(){
        if (mp==null){
            mp= MediaPlayer.create(this,R.raw.kalimba);
            mp.setVolume(0.7f,0.7f);
            mp.setLooping(true);
        }
        
        if (!mp.isPlaying())mp.start();
    }
    public void pauseMusic(){
        if (mp!=null && mp.isPlaying())mp.pause();
    }
    public void stopMusic(){
        if (mp!=null){
            mp.stop();
            mp.release();
            mp=null;
        }

    }
}
