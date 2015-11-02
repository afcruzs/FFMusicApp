package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.youtube_connection.VideoItem;

/**
 * {@link BaseAdapter} personalizado para el gridview
 */
public class YoutubeItemAdapter extends RecyclerView.Adapter<YoutubeItemAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<VideoItem> list;
    private View.OnClickListener listener;

    public YoutubeItemAdapter(ArrayList<VideoItem> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @Override
    public YoutubeItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.video_item, viewGroup, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Song selected ", Toast.LENGTH_LONG).show();
            }
        });
        ViewHolder view = new ViewHolder(v);

        return view;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(YoutubeItemAdapter.ViewHolder viewHolder, int i) {
        VideoItem item = list.get(i);
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
        private ImageView image;

        public ViewHolder(View v) {
            super(v);
            mNameSong = (TextView)v.findViewById(R.id.name_song);
            image = (ImageView) v.findViewById(R.id.image_song);
        }

        public void bindHolder(VideoItem t) {
            mNameSong.setText(t.getTitle());
            image.setImageResource(R.drawable.test);


           // new DownloadImageTask(image,t.getSongId().toString()).execute(t.getThumbnailURL());
        }
    }
}
