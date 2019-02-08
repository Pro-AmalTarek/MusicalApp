package com.amaltarek.musicalapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amaltarek.musicalapp.Adapters.CategoryAdapter;
import com.amaltarek.musicalapp.Model.Album;
import com.amaltarek.musicalapp.Model.Song;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final ArrayList<Album> mAlbums= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // First, Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.viewPager);

        //Second, Give the tabsLayout to the ViewPager
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        initAlbums();
        // Create ArrayList of Albums

        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager(), this);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
    }

    private void initAlbums() {
       mAlbums.add(new Album(R.drawable.massoudcurtis, R.string.album_tabssam, R.string.artist_massoud, getAlbumSongs(1)));
    }

    private ArrayList<Song> getAlbumSongs(int position){
        ArrayList<Song> songs = new ArrayList<>();
        switch(position){
            case 1:
                songs.add(new Song(1, R.string.song_tabssam, R.raw.tabssam, R.string.artist_massoud, R.string.album_tabssam));
                songs.add(new Song(2, R.string.song_assalatu, R.raw.assalatu_wassalamu, R.string.artist_massoud, R.string.album_tabssam));
                break;
            default:
                return songs;
        }
        return songs;
    }


}
