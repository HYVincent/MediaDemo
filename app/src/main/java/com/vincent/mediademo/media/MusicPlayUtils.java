package com.vincent.mediademo.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: MusicPlayUtils
 * Author: Vincent
 * Date: 2019/8/6 23:13
 * Description: 描述
 * History:
 */
public class MusicPlayUtils {

    private static MusicPlayUtils instance;
    private Context mContext;
    private MediaPlayer mediaPlayer;
    private static final String TAG = "音乐播放";
    private boolean isPlay = false;//true 正在播放 false 播放完成

    /**
     * 初始化
     *
     * @param mContext
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    public boolean play(Context mContext, int musicResId, final MediaStatusOnListener mediaStatusOnListener) {
        this.mContext = mContext;
        if(isPlay){
            Log.d(TAG, "play: 正在播放..");
            return true;
        }
        mediaPlayer = MediaPlayer.create(mContext, musicResId);
        mediaPlayer.start();
        isPlay = true;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //播放结束
                Log.d(TAG, "onCompletion: 播放结束..");
                mediaPlayer.reset();
                isPlay = false;
                mediaStatusOnListener.onCompletion(mediaPlayer);
            }
        });
        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                Log.d(TAG, "onInfo: ");
                return  mediaStatusOnListener.onInfo(mediaPlayer,i,i1);
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Log.d(TAG, "onError: 播放出错了..");
//                mediaStatusOnListener.onError(mediaPlayer,i,i1);
                return mediaStatusOnListener.onError(mediaPlayer,i,i1);
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Log.d(TAG, "onPrepared: 准备就绪，即将播放..");
                //在这里可以设置播放的音量 可设置属性，比如视频的音量等等..
                mediaStatusOnListener.onPrepared(mediaPlayer);
            }
        });
        return true;
    }

    public static MusicPlayUtils getInstance() {
        if (instance == null) {
            instance = new MusicPlayUtils();
        }
        return instance;
    }


}
