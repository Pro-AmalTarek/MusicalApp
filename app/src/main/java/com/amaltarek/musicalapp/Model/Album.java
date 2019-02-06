package com.amaltarek.musicalapp.Model;

import java.util.ArrayList;

public class Album {

    /** Art Resource ID of the Album */
    private int mAlbumArtResourceID;

    /** String resource ID for Name of The Album */
    private int mAlbumName;

    /** String resource ID for Artist Name of the Album */
    private int mArtistName;

    /** Array List of Songs of the Album */
    private ArrayList<Song> mAlbumSongs;

    /** Create Album Object */
    public Album(int albumArtResourceID, int albumName, int artistName, ArrayList<Song> albumSongs) {
        mAlbumArtResourceID = albumArtResourceID;
        mAlbumName = albumName;
        mArtistName = artistName;
        mAlbumSongs = albumSongs;
    }

    public int getAlbumArtResourceID() {
        return mAlbumArtResourceID;
    }

    public int getAlbumName() {
        return mAlbumName;
    }

    public int getArtistName() {
        return mArtistName;
    }

    public ArrayList<Song> getAlbumSongs() {
        return mAlbumSongs;
    }
}
