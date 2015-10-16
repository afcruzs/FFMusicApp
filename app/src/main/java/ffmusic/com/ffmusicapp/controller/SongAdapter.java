package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
        private  ImageButton like;
        private ImageButton delete;
        private boolean isLike;
        public ViewHolder(View v) {
            super(v);
            mNameSong = (TextView)v.findViewById(R.id.name_song);
            delete =  (ImageButton)v.findViewById(R.id.delete_song);
            like = (ImageButton)v.findViewById(R.id.like_buttom);
            isLike = false;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Pulsado el elemento ", Toast.LENGTH_LONG).show();
                }
            });
            delete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Pulsado el elemento xx", Toast.LENGTH_LONG).show();
                }
            });
            like.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if( !isLike )
                        like.setImageResource(R.drawable.ic_dislike);
                    else
                        like.setImageResource(R.drawable.ic_like);
                    isLike = !isLike;
                    Toast.makeText(v.getContext(), "Pulsado el elemento xx", Toast.LENGTH_LONG).show();
                }
            });
        }


        public void bindHolder(ListModelItem t) {
            mNameSong.setText(t.getName());
        }
    }

}