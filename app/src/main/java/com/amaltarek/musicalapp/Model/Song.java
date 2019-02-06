package com.amaltarek.musicalapp.Model;

public class Song {

    /** Number of the Song */
    private int mSongNumber;

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
        mSongNumber = songNumber;
        mSongName = songName;
        mSongResourceID = songResourceID;
        mArtistName = artistName;
        mAlbumName = albumName;
    }

    public int getSongNumber() {
        return mSongNumber;
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
