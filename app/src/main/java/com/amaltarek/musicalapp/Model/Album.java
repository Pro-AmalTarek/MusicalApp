package com.amaltarek.musicalapp.Model;

import java.util.ArrayList;

public class Album {

    /** Art Resource ID of the Album */
    private int mAlbumArtResourceID;

    /** Name of The Album */
    private String mAlbumName;

    /** Artist Name of the Album */
    private String mArtistName;

    /** Array List of Songs of the Album */
    private ArrayList<Song> mAlbumSongs;

    /** Create Album Object */
    public Album(int albumArtResourceID, String albumName, String artistName, ArrayList<Song> albumSongs) {
        mAlbumArtResourceID = albumArtResourceID;
        mAlbumName = albumName;
        mArtistName = artistName;
        mAlbumSongs = albumSongs;
    }

    public int getAlbumArtResourceID() {
        return mAlbumArtResourceID;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public ArrayList<Song> getAlbumSongs() {
        return mAlbumSongs;
    }
}
