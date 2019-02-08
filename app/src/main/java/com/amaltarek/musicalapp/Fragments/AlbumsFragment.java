package com.amaltarek.musicalapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.amaltarek.musicalapp.Adapters.AlbumAdapter;
import com.amaltarek.musicalapp.Model.Album;
import com.amaltarek.musicalapp.Model.Song;
import com.amaltarek.musicalapp.R;
import com.amaltarek.musicalapp.Activities.SongsActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment {

    public AlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_albums, container, false);

        // Create ArrayList of Albums
        final ArrayList<Album> albums = new ArrayList<>();
        albums.add(new Album(R.drawable.massoudcurtis, R.string.album_tabssam, R.string.artist_massoud, getAlbumSongs(1)));
        albums.add(new Album(R.drawable.maherzain, R.string.album_Thank, R.string.artist_maher, getAlbumSongs(2)));
        albums.add(new Album(R.drawable.forgive_me, R.string.album_forgive_me, R.string.artist_maher, getAlbumSongs(3)));

        // to Shuffle ArrayList of Albums every time open the App
        Collections.shuffle(albums);

        // Create an {@link AlbumAdapter}, whose data source is a list of {@link Song}s.
        AlbumAdapter adapter = new AlbumAdapter(getActivity(), albums);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        GridView albumsList =  rootView.findViewById(R.id.albums_grid);

        // Make the {@link ListView} use the {@link AlbumAdapter} we created above.
        albumsList.setAdapter(adapter);

        // Set a click listener to open the songs when the album list item is clicked on
        albumsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SongsActivity.class);
                Bundle songsBundle = new Bundle();
                songsBundle.putSerializable("EXTRA_SONGS",(Serializable) albums.get(position).getAlbumSongs());
                intent.putExtra("BUNDLE",songsBundle);
                startActivity(intent);
            }
        });

        return rootView;
    }

    /**
     * Get Album Songs for each Album by Position
     * @param position
     * @return
     */
    private ArrayList<Song> getAlbumSongs(int position){
        ArrayList<Song> songs = new ArrayList<>();
        switch(position){
            case 1:
                songs.add(new Song(R.drawable.massoudcurtis, R.string.song_tabssam, R.raw.tabssam, R.string.artist_massoud, R.string.album_tabssam));
                songs.add(new Song(R.drawable.massoudcurtis, R.string.song_assalatu, R.raw.assalatu_wassalamu, R.string.artist_massoud, R.string.album_tabssam));
                break;
            case 2:
                songs.add(new Song(R.drawable.maherzain, R.string.song_ya_napi, R.raw.ya_nabi_salam_alayka, R.string.artist_maher, R.string.album_Thank));
                songs.add(new Song(R.drawable.maherzain, R.string.insha_allah, R.raw.insha_allah, R.string.artist_maher, R.string.album_Thank));
                songs.add(new Song(R.drawable.maherzain, R.string.palestine_will_be_free, R.raw.palestine_will_be_free, R.string.artist_maher, R.string.album_Thank));
                songs.add(new Song(R.drawable.maherzain, R.string.thank_you_allah, R.raw.thank_you_allah, R.string.artist_maher, R.string.album_Thank));
                songs.add(new Song(R.drawable.maherzain, R.string.always_be_there, R.raw.always_be_there, R.string.artist_maher, R.string.album_Thank));
                songs.add(new Song(R.drawable.maherzain, R.string.the_chosen_one, R.raw.the_chosen_one, R.string.artist_maher, R.string.album_Thank));
               break;
            case 3:
                songs.add(new Song(R.drawable.maherzain, R.string.number_one_for_me, R.raw.number_one_for_me, R.string.artist_maher, R.string.forgive_me));
                songs.add(new Song(R.drawable.maherzain, R.string.assalamu_alayka, R.raw.assalamu_alayka, R.string.artist_maher, R.string.forgive_me));
                songs.add(new Song(R.drawable.maherzain, R.string.masha_allah, R.raw.masha_allah, R.string.artist_maher, R.string.forgive_me));
                songs.add(new Song(R.drawable.maherzain, R.string.mawlaya, R.raw.mawlaya, R.string.artist_maher, R.string.forgive_me));
                songs.add(new Song(R.drawable.maherzain, R.string.forgive_me, R.raw.forgive_me, R.string.artist_maher, R.string.forgive_me));
                songs.add(new Song(R.drawable.maherzain, R.string.assalamu_alayka_arabic, R.raw.assalamu_alayka_arabic, R.string.artist_maher, R.string.forgive_me));
                break;
        }
        return songs;
    }

}
