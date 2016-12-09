package com.karen.musicplayer.core;

import java.io.Serializable;

/**
 * Created by Lenovo on 12/7/2016.
 */
public class Song implements Serializable {
    private long id;
    private String title;
    private String artist;

    public Song(long id, String artist, String title) {
        this.id = id;
        this.artist = artist;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}
