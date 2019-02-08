package com.amaltarek.musicalapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaltarek.musicalapp.Model.Song;
import com.amaltarek.musicalapp.R;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context,0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_item, parent, false);
        Song currentSong = getItem(position);

        TextView songNumber = convertView.findViewById(R.id.song_number);
        songNumber.setText(String.valueOf(position + 1));

        TextView songName = convertView.findViewById(R.id.song_name);
        songName.setText(currentSong.getSongName());

        TextView songDetails = convertView.findViewById(R.id.song_details);
        songDetails.setText(getContext().getString(currentSong.getArtistName()) + " - " + getContext().getString(currentSong.getAlbumName()));

        return convertView;
    }
}
