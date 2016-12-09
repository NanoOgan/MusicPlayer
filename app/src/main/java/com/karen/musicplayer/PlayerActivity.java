package com.karen.musicplayer;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.karen.musicplayer.core.CorePlayer;
import com.karen.musicplayer.core.Song;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 12/8/2016.
 */
public class PlayerActivity extends AppCompatActivity {
    private boolean trackIsLoaded = false;
    private boolean isPlaying = false;
    @BindView(R.id.playerArtistTextView)
    public TextView mArtistTextView;
    @BindView(R.id.playerTitleTextView)
    public TextView mTitleTextView;
    @BindView(R.id.buttonView)
    public ImageView mButtonView;
    private static long currentSongID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Song song = (Song)intent.getExtras().getSerializable(SongAdapter.SONG_INTENT_DATA );
        long id = song.getId();
        mArtistTextView.setText(song.getArtist());
        mTitleTextView.setText(song.getTitle());
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                id);
        final Bitmap stop = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.stop);
        final Bitmap start =  BitmapFactory.decodeResource(this.getResources(),
                R.drawable.play);

        final MediaPlayer player = CorePlayer.getPlayer();
        try {
            if(currentSongID != id) {
                player.reset();
                player.setDataSource(getApplicationContext() , trackUri);
                player.prepare();
            }
            currentSongID = id;
            trackIsLoaded = true;
            isPlaying = true;
            ImageViewAnimatedChange(this , mButtonView ,stop);
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(trackIsLoaded){
                    if(isPlaying){
                        player.pause();
                        ImageViewAnimatedChange(view.getContext() , mButtonView ,start);
                    }
                    else{
                        player.start();
                        ImageViewAnimatedChange(view.getContext() , mButtonView ,stop);
                    }
                    isPlaying = !isPlaying;
                }
            }
        });
    }
    public static void ImageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                v.setImageBitmap(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }
}
