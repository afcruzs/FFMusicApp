package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.SongRoom;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.DeleteSongRoomAsyncTask;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> implements View.OnClickListener{

    private ArrayList<ListModelItem> list;
    private ArrayList<SongRoom> songRoomList;
    private View.OnClickListener listener;
    private Room room;
    private PlayListFragment playListFragment;
    private Context context;

    public SongAdapter(ArrayList<ListModelItem> list, Room room, PlayListFragment playListFragment, Context context) {
        this.list = list;
        this.room = room;
        this.playListFragment = playListFragment;
        this.context = context;
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.room_play_list_item, viewGroup, false);
        v.setOnClickListener(this);
        ViewHolder view = new ViewHolder(v);

        return view;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setSongRoomList(ArrayList<SongRoom> songRoomList) {
        this.songRoomList = songRoomList;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(SongAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bindHolder(list.get(i), songRoomList.get(i));
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


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameSong;
        private ImageView image;
        private Toolbar toolbar;

        private static final int MENU_DELETE = R.id.delete_song;
        private static final int MENU_LIKE = R.id.like;

        public ViewHolder(View v) {
            super(v);
            mNameSong = (TextView)v.findViewById(R.id.name_song);
            image = (ImageView) v.findViewById(R.id.image_song);
            toolbar = (Toolbar)v.findViewById(R.id.play_list_menu_item_bar);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


        public void bindHolder(final ListModelItem t, final SongRoom songRoom) {
            mNameSong.setText(t.getName());

            if ( room.getRoomOwner().getId().equals(songRoom.getRoom().getRoomOwner().getId()) ) {
                toolbar.getMenu().clear();
                toolbar.inflateMenu( R.menu.menu_room_play_list_item_owner);

                toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Log.d("menu", "owner" + menuItem.getItemId());
                        int id = menuItem.getItemId();
                        switch ( id ) {
                            case MENU_DELETE:
                                Log.d("borrar", "1. " + songRoom.getId() + " " + songRoom.getSong().getId());
                                new DeleteSongRoomAsyncTask(context) {
                                    @Override
                                    public void onPreExecute(){}

                                    @Override
                                    public void onPostExecute(SongRoom data) {
                                        Log.d("borrar", "2. " + data.getId() + " " + data.getIdxInQueue());
                                        playListFragment.updateSongs();
                                    }
                                }.execute(t.getDBId());
                                break;
                            default:
                        }
                        return true;
                    }
                });
            } else {
                toolbar.getMenu().clear();
                toolbar.inflateMenu( R.menu.menu_room_play_list_item_member);

                toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Log.d("menu", "member" + menuItem.getItemId());
                        int id = menuItem.getItemId();
                        switch ( id ) {
                            case MENU_LIKE:

                                break;
                            default:
                        }
                        return true;
                    }
                });
            }
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