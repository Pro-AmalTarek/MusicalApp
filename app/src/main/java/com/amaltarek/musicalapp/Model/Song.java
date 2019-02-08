package com.amaltarek.musicalapp.Model;

import java.io.Serializable;
public class Song implements Serializable{

    /** Art of the Song */
    private int mSongArtResourceID;

    /** String resource ID for Song Name */
    private int mSongName;

    /** Audio Resource ID for Song */
    private int mSongResourceID;

    /** String resource ID for Artist Name of the Song */
    private int mArtistName;

    /** String resource ID for Album Name of the song */
    private int mAlbumName;

    /** Create a Song Object */
    public Song(int songNumber, int songName, int songResourceID, int artistName, int albumName) {
        mSongArtResourceID = songNumber;
        mSongName = songName;
        mSongResourceID = songResourceID;
        mArtistName = artistName;
        mAlbumName = albumName;
    }

    public int getSongArtResourceID() {
        return mSongArtResourceID;
    }

    public int getSongName() {
        return mSongName;
    }

    public int getSongResourceID() {
        return mSongResourceID;
    }

    public int getArtistName() {
        return mArtistName;
    }

    public int getAlbumName() {
        return mAlbumName;
    }
}
