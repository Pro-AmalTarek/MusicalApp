package com.amaltarek.musicalapp.Model;

public class Song {

    /** Number of the Song */
    private int mSongNumber;

    /** Song Name */
    private String mSongName;

    /** Audio Resource ID for Song */
    private int mSongResourceID;

    /** Artist Name of the Song */
    private String mArtistName;

    /** Album Name of the song */
    private String mAlbumName;

    /** Create a Song Object */
    public Song(int songNumber, String songName, int songResourceID, String artistName, String albumName) {
        mSongNumber = songNumber;
        mSongName = songName;
        mSongResourceID = songResourceID;
        mArtistName = artistName;
        mAlbumName = albumName;
    }

    public int getSongNumber() {
        return mSongNumber;
    }

    public String getSongName() {
        return mSongName;
    }

    public int getSongResourceID() {
        return mSongResourceID;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }
}
