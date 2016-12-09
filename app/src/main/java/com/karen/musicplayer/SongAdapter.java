package com.karen.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karen.musicplayer.core.Song;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 12/7/2016.
 */
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {
    public static final String SONG_INTENT_DATA = "com.karen.musicplayer.song_intent_data";
    private ArrayList<Song> songList;

    public SongAdapter(ArrayList<Song> songList) {
        this.songList = songList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Song song = songList.get(position);
        holder.songArtist.setText(song.getArtist());
        holder.songTitle.setText(song.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext() , PlayerActivity.class);
                intent.putExtra(SONG_INTENT_DATA , song);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.songArtist)
        public TextView songTitle;
        @BindView(R.id.songTitle)
        public TextView songArtist;
        @BindView(R.id.card_view)
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this , view);
        }
    }
}
