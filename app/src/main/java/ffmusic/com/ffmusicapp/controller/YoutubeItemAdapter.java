package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.Song;
import com.ffmusic.backend.ffMusicApi.model.SongRoom;

import java.util.ArrayList;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.CountRoomSongsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.SaveSongAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.SaveSongRoom;
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
    public YoutubeItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.video_item, viewGroup, false);


        ViewHolder view = new ViewHolder(v,context);

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
        private VideoItem item;


        public ViewHolder(View v, final Context mContext) {
            super(v);
            mNameSong = (TextView)v.findViewById(R.id.name_song);
            image = (ImageView) v.findViewById(R.id.image_song);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new CountRoomSongsAsyncTask(mContext){
                        @Override
                        public void onPostExecute(Integer roomSize){
                            final SongRoom songRoom = new SongRoom();
                            Song realSong = new Song();

                            realSong.setSongName(item.getTitle());
                            realSong.setSongYoutubeId(item.getId());
                            realSong.setArtist(item.getDescription());
                            realSong.setThumbnailURL(item.getThumbnailURL());

                            songRoom.setRoom(RoomActivity.room);
                            songRoom.setSong(realSong);
                            songRoom.setVotes(0);
                            Log.d("guardar", roomSize + "");
                            songRoom.setIdxInQueue(roomSize);
                            songRoom.setCreatedBy(LoginActivity.currentUser);
                            new SaveSongAsyncTask(mContext){
                                @Override
                                public void onPostExecute( Song data ){
                                    super.onPostExecute(data);
                                    Log.d("xd", "Song saved " + data.getSongYoutubeId());
                                    songRoom.setSong(data);
                                    new SaveSongRoom(mContext){
                                        @Override
                                        public void onPostExecute( SongRoom songRoom ){
                                            super.onPostExecute(songRoom);
                                            Log.d("xd", "SongRoom saved");
                                            final Intent intent = new Intent(mContext, RoomActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            intent.putExtra(RoomActivity.CURRENT_ROOM,  RoomActivity.room.getId() );
                                            context.startActivity(intent);
                                        }
                                    }.execute( songRoom );
                                }
                            }.execute(realSong);

                            Log.d("xd", "Song selected --> " + String.valueOf( item.toString() ) );
                        }
                    }.execute(RoomActivity.room.getId());
                }
            });
        }


        public void bindHolder(VideoItem t) {
            this.item = t;
            mNameSong.setText(t.getTitle());
            image.setImageResource(R.drawable.test);
            new DownloadImageTask(image,t.getTitle()).execute(t.getThumbnailURL());
           // new DownloadImageTask(image,t.getSongId().toString()).execute(t.getThumbnailURL());
        }
    }
}
