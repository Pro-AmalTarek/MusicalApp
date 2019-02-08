package com.amaltarek.musicalapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.amaltarek.musicalapp.Model.Song;
import com.amaltarek.musicalapp.R;

import java.util.ArrayList;

public class NowPlaying extends AppCompatActivity {

    /**
     * Handler of Seek Bar
     */
    private final Handler handler = new Handler();
    /**
     * Seek Bar for Song Duration
     */
    private SeekBar mSongSeekBar;
    /**
     * ArrayList of Album Songs
     */
    private ArrayList<Song> mSongs;
    /**
     * Position of Playing Song
     */
    private int mSongPosition;
    /**
     * Image View of Play or Pause Button
     */
    private ImageView mPlayImg;
    /**
     * boolean var to know if song is paused or not
     */
    private boolean mPlayStatus = true;
    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;
    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };
    /**
     * The Move seek bar. Thread to move seekbar based on the current position
     * of the song
     */
    private Runnable mMoveSeekBarThread = new Runnable() {
        public void run() {
            if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {

                int mediaPos_new = mMediaPlayer.getCurrentPosition();
                int mediaMax_new = mMediaPlayer.getDuration();
                mSongSeekBar.setMax(mediaMax_new);
                mSongSeekBar.setProgress(mediaPos_new);

                handler.postDelayed(this, 100); //Looping the thread after 0.1 second
            }

        }
    };
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, repeat go to next Song
            mSongPosition = (mSongPosition + 1) % mSongs.size();
            updateCurrentSong();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        // Get Array List of Songs
        Intent intent = getIntent();
        Bundle songsBundle = intent.getBundleExtra("BUNDLE");
        mSongs = (ArrayList<Song>) songsBundle.getSerializable("EXTRA_SONGS");
        mSongPosition = songsBundle.getInt("SONG_POSITION");

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        // Seek Bar get the position that user will seek to
        mSongSeekBar = findViewById(R.id.seekbar);
        mSongSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying() && arg2)
                    mMediaPlayer.seekTo(arg1);
            }
        });

        //Update the Content of the Current Song and Play the Song
        updateCurrentSong();
    }

    /**
     * Update the UI of the Current Song and Play the Song
     */
    private void updateCurrentSong() {
        //First get Song Position
        Song currentSong = mSongs.get(mSongPosition);
        mPlayImg = findViewById(R.id.play_song);

        // Set Song Image Resource ID
        ImageView songArt = findViewById(R.id.song_art);
        songArt.setImageResource(currentSong.getSongArtResourceID());

        // Set Song Name and Details
        TextView songName = findViewById(R.id.song_name);
        songName.setText(currentSong.getSongName());

        TextView songDetails = findViewById(R.id.song_details);
        songDetails.setText(this.getString(currentSong.getArtistName()) + " - " + this.getString(currentSong.getAlbumName()));

        // Finally Play Song
        playSong(currentSong);
    }

    /**
     * Request audio focus so in order to play the audio file.
     *
     * @param song
     */
    public void playSong(Song song) {
        // Release the media player if it currently exists because we are about to
        // play a different sound file
        releaseMediaPlayer();

        // Request audio focus so in order to play the audio file. The app needs to play a
        // short audio file, so we will request audio focus with a short amount of time
        // with AUDIOFOCUS_GAIN_TRANSIENT.
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // We have audio focus now.

            // Create and setup the {@link MediaPlayer} for the audio resource associated
            // with the current song
            mMediaPlayer = MediaPlayer.create(this, song.getSongResourceID());

            // Set the Seek Bar
            int mediaPos = mMediaPlayer.getCurrentPosition();
            int mediaMax = mMediaPlayer.getDuration();

            mSongSeekBar.setMax(mediaMax); // Set the Maximum range of the
            mSongSeekBar.setProgress(mediaPos);// set current progress to song's

            handler.removeCallbacks(mMoveSeekBarThread);
            handler.postDelayed(mMoveSeekBarThread, 100); //call the thread after 100 milliseconds

            // Start the audio file
            mMediaPlayer.start();

            // Setup a listener on the media player, so that we can stop and release the
            // media player once the sound has finished playing.
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    /**
     * On Click on pause , play , Previous or Next Buttons get the Right or pause the Song
     * @param view
     */
    public void getSong(View view) {
        switch (view.getId()) {
            case R.id.play_song:
                // Play or Pause Song
                if (mPlayStatus) {
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        mMediaPlayer.pause();
                        mPlayImg.setImageResource(R.drawable.ic_play);
                        mPlayStatus = false;
                    }
                } else {
                    if (mMediaPlayer != null) {
                        mMediaPlayer.start();
                        handler.postDelayed(mMoveSeekBarThread, 100); //call the thread after 100 milliseconds
                        mPlayImg.setImageResource(R.drawable.ic_pause);
                        mPlayStatus = true;
                    }
                }
                break;
            case R.id.prev_song:
                // Previous Song Button
                mSongPosition = (mSongPosition - 1) % mSongs.size();
                if (mSongPosition < 0)
                    mSongPosition = mSongs.size() - 1;
                updateCurrentSong();
                break;
            case R.id.next_song:
                // Forward Song Button
                mSongPosition = (mSongPosition + 1) % mSongs.size();
                updateCurrentSong();
                break;
        }
    }
}
