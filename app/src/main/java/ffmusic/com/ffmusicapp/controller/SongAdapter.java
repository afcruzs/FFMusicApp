package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ffmusic.com.ffmusicapp.R;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> implements View.OnClickListener{

    private ArrayList<ListModelItem> list;
    private View.OnClickListener listener;

    public SongAdapter(ArrayList<ListModelItem> list) {

        this.list = list;
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.room_play_list_item, viewGroup, false);
        v.setOnClickListener(this);
        ViewHolder view = new ViewHolder(v);

        return view;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(SongAdapter.ViewHolder viewHolder, int i) {
        ListModelItem item = list.get(i);
        viewHolder.bindHolder(item);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void onClick(View v) {
        if( listener!=null ){
            listener.onClick(v);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameSong;
        private TextView artist;
        private ImageView image;
        private ImageView star;
        private int mLastResourceId;

        public ViewHolder(View v) {
            super(v);
            mNameSong = (TextView)v.findViewById(R.id.name_song);
            artist = (TextView)v.findViewById(R.id.name_artist);
            image = (ImageView) v.findViewById(R.id.image_song);
            star = (ImageView) v.findViewById(R.id.star);
            mLastResourceId = android.R.drawable.star_off;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            star.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if( mLastResourceId == android.R.drawable.star_off){
                        mLastResourceId = android.R.drawable.star_on;
                        star.setImageResource(android.R.drawable.star_on);
                    }else{
                        mLastResourceId = android.R.drawable.star_off;
                        star.setImageResource(android.R.drawable.star_off);
                    }
                }
            });

        }

        public void bindHolder(ListModelItem t) {

            mNameSong.setText(t.getName());
            artist.setText(t.getArtist());
            /*if( t.getName().equals("Someday")) {
                image.setImageResource(R.drawable.test);
            }
            if( t.getName().equals("Resistance")){
                image.setImageResource(R.drawable.muse);
            }
            if( t.getName().equals("The man who sold the world")){

            }*/

            new DownloadImageTask(image,t.getSongId().toString()).execute(t.getThumbnailURL());
        }
    }



}