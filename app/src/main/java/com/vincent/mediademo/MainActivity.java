package com.vincent.mediademo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.vincent.mediademo.media.MediaStatusOnListener;
import com.vincent.mediademo.media.MusicPlayUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "首页";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                MusicPlayUtils.getInstance().play(MainActivity.this, R.raw.rise, new MediaStatusOnListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                    }

                    @Override
                    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                        return false;
                    }

                    @Override
                    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                        Log.d(TAG, "onError: ............");
                        return false;
                    }

                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        Log.d(TAG, "onPrepared: 准备完毕，开始播放..");
                    }
                });
            }
        });
    }
}
