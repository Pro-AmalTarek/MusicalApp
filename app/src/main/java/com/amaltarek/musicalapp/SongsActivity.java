package com.amaltarek.musicalapp;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amaltarek.musicalapp.Fragments.SongsFragment;
import com.amaltarek.musicalapp.Model.Song;

import java.io.Serializable;
import java.util.ArrayList;

public class SongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        // Get Array List of Songs
        Intent intent = getIntent();
        Bundle songsBundle = intent.getBundleExtra("BUNDLE");
        ArrayList<Song> songs = (ArrayList<Song>) songsBundle.getSerializable("EXTRA_SONGS");

        // Fragment Manager and Start Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Create Song Fragment and bundle Array List of Songs
        Fragment songFragment = new SongsFragment();
        Bundle args = new Bundle();
        args.putSerializable("EXTRA_SONGS",(Serializable)songs);
        songFragment.setArguments(args);

        // Add Fragment to Activity then Commit
        fragmentTransaction.add(R.id.fragment_songs, songFragment);
        fragmentTransaction.commit();
    }
}
