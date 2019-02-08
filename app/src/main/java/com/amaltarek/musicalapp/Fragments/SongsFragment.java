package com.amaltarek.musicalapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amaltarek.musicalapp.Adapters.SongAdapter;
import com.amaltarek.musicalapp.Model.Song;
import com.amaltarek.musicalapp.Activities.NowPlaying;
import com.amaltarek.musicalapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongsFragment extends Fragment {



    public SongsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_songs, container, false);

        // Check if there songs coming from another fragment or create new one
        Bundle arguments = getArguments();
        final ArrayList<Song> mSongs;
        if(arguments != null && arguments.containsKey("EXTRA_SONGS")){
            mSongs = (ArrayList<Song>) getArguments().getSerializable("EXTRA_SONGS");
        }else{
            // Create ArrayList of Songs
            mSongs = new ArrayList<>();
            mSongs.add(new Song(R.drawable.massoudcurtis, R.string.song_tabssam, R.raw.tabssam, R.string.artist_massoud, R.string.album_tabssam));
            mSongs.add(new Song(R.drawable.massoudcurtis, R.string.song_assalatu, R.raw.assalatu_wassalamu, R.string.artist_massoud, R.string.album_tabssam));
            mSongs.add(new Song(R.drawable.maherzain, R.string.song_ya_napi, R.raw.ya_nabi_salam_alayka, R.string.artist_maher, R.string.album_Thank));
            mSongs.add(new Song(R.drawable.maherzain, R.string.insha_allah, R.raw.insha_allah, R.string.artist_maher, R.string.album_Thank));
            mSongs.add(new Song(R.drawable.maherzain, R.string.palestine_will_be_free, R.raw.palestine_will_be_free, R.string.artist_maher, R.string.album_Thank));
            mSongs.add(new Song(R.drawable.maherzain, R.string.thank_you_allah, R.raw.thank_you_allah, R.string.artist_maher, R.string.album_Thank));
            mSongs.add(new Song(R.drawable.maherzain, R.string.always_be_there, R.raw.always_be_there, R.string.artist_maher, R.string.album_Thank));
            mSongs.add(new Song(R.drawable.maherzain, R.string.the_chosen_one, R.raw.the_chosen_one, R.string.artist_maher, R.string.album_Thank));
            mSongs.add(new Song(R.drawable.forgive_me, R.string.number_one_for_me, R.raw.number_one_for_me, R.string.artist_maher, R.string.forgive_me));
            mSongs.add(new Song(R.drawable.forgive_me, R.string.assalamu_alayka, R.raw.assalamu_alayka, R.string.artist_maher, R.string.forgive_me));
            mSongs.add(new Song(R.drawable.forgive_me, R.string.masha_allah, R.raw.masha_allah, R.string.artist_maher, R.string.forgive_me));
            mSongs.add(new Song(R.drawable.forgive_me, R.string.mawlaya, R.raw.mawlaya, R.string.artist_maher, R.string.forgive_me));
            mSongs.add(new Song(R.drawable.forgive_me, R.string.forgive_me, R.raw.forgive_me, R.string.artist_maher, R.string.forgive_me));
            mSongs.add(new Song(R.drawable.forgive_me, R.string.assalamu_alayka_arabic, R.raw.assalamu_alayka_arabic, R.string.artist_maher, R.string.forgive_me));


            // to Shuffle ArrayList of Songs
            Collections.shuffle(mSongs);
        }

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
                Intent intent = new Intent(getActivity(), NowPlaying.class);
                Bundle songsBundle = new Bundle();
                songsBundle.putSerializable("EXTRA_SONGS",(Serializable) mSongs);
                songsBundle.putInt("SONG_POSITION", position);
                intent.putExtra("BUNDLE",songsBundle);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
