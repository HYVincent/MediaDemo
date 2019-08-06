package com.vincent.mediademo.media;

import android.media.MediaPlayer;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: MediaStatusOnListener
 * Author: Vincent
 * Date: 2019/8/6 23:56
 * Description: 描述
 * History:
 */
public interface MediaStatusOnListener {
    /**
     * 播放结束
     * @param mediaPlayer
     */
     void onCompletion(MediaPlayer mediaPlayer);

    boolean onInfo(MediaPlayer mediaPlayer, int i, int i1);

    /**
     * 播放出错了
     * @param mediaPlayer
     * @param i
     * @param i1
     * @return
     */
    boolean onError(MediaPlayer mediaPlayer, int i, int i1);

    /**
     * 播放准备就绪，即将播放
     * @param mediaPlayer
     */
    void onPrepared(MediaPlayer mediaPlayer);
}
