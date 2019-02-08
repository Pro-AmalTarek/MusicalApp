package com.amaltarek.musicalapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaltarek.musicalapp.Model.Album;
import com.amaltarek.musicalapp.Model.Song;
import com.amaltarek.musicalapp.R;

import java.util.ArrayList;

public class AlbumAdapter extends ArrayAdapter<Album> {

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        super(context,0, albums);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.album_item, parent, false);
        Album currentAlbum = getItem(position);

        // Album Art
        ImageView albumArt = convertView.findViewById(R.id.album_art);
        albumArt.setImageResource(currentAlbum.getAlbumArtResourceID());

        //Album Name Text View
        TextView albumName = convertView.findViewById(R.id.album_name);
        albumName.setText(currentAlbum.getAlbumName());

        //Artist Name for each Album
        TextView artistName = convertView.findViewById(R.id.artist_name);
        artistName.setText(currentAlbum.getArtistName());

        return convertView;
    }
}
