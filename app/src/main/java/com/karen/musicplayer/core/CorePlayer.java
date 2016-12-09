package com.karen.musicplayer.core;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;

/**
 * Created by Lenovo on 12/9/2016.
 */
public class CorePlayer {
    public static MediaPlayer player;

    public static MediaPlayer getPlayer(){
        if(player == null)
            player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        return player;
    }
}
