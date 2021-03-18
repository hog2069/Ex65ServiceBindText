package com.hog2020.ex65servicebindtext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //연결하기 MusicService
    @Override
    protected void onResume() {
        super.onResume();

        if (musicService==null){
            Intent intent= new Intent(this,MusicService.class);
            startService(intent);

            bindService(intent,connection,0);
        }
    }

    ServiceConnection connection= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            MusicService.MyBinder myBinder=(MusicService.MyBinder) binder;
            musicService = myBinder.getServiceObject();

            Toast.makeText(MainActivity.this, "binded", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void clickstart(View view) {
        if (musicService != null)musicService.startMusic();
    }

    public void clickpause(View view) {
        if (musicService != null)musicService.pauseMusic();
    }

    public void clickstop(View view) {
        if (musicService != null){
            musicService.stopMusic();
            unbindService(connection);
            musicService=null;
        }

        Intent intent =new Intent(this,MusicService.class);
        stopService(intent);

        finish();
    }
}