package com.amaltarek.musicalapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amaltarek.musicalapp.Adapters.SongAdapter;
import com.amaltarek.musicalapp.Model.Song;
import com.amaltarek.musicalapp.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongsFragment extends Fragment {


    ArrayList<Song> mSongs;

    public SongsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if(arguments != null && arguments.containsKey("EXTRA_SONGS")){
            mSongs = (ArrayList<Song>) getArguments().getSerializable("EXTRA_SONGS");
        }else{
            // Create ArrayList of Songs
            mSongs = new ArrayList<>();
            mSongs.add(new Song(1, R.string.song_tabssam, R.raw.tabssam, R.string.artist_massoud, R.string.album_tabssam));
            mSongs.add(new Song(2, R.string.song_assalatu, R.raw.assalatu_wassalamu, R.string.artist_massoud, R.string.album_tabssam));
            mSongs.add(new Song(3, R.string.song_ya_napi, R.raw.ya_nabi_salam_alayka, R.string.artist_maher, R.string.album_Thank));

            // to Shuffle ArrayList of Songs
            Collections.shuffle(mSongs);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_songs, container, false);

        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s.
        SongAdapter adapter = new SongAdapter(getActivity(), mSongs);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        ListView songsList = (ListView) rootView.findViewById(R.id.songs_list);

        // Make the {@link ListView} use the {@link SongAdapter} we created above.
        songsList.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        songsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return rootView;
    }

}
