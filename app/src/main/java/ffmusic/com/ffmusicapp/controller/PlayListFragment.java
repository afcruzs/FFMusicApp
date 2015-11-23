package ffmusic.com.ffmusicapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.SongRoom;
import com.ffmusic.backend.ffMusicApi.model.UserEnteredRoom;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.internal.ac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetRoomByIdAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.GetRoomSongsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.InsertUserEnteredRoomAsyncTask;

/**
 * Created by Milder on 21/11/2015.
 */
public class PlayListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ArrayList<ListModelItem> list;
    private YouTubePlayer youTubePlayer;

    private RecyclerView mRecyclerView;
    private SongAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static Room room;
    private Long currentRoom;
    private RoomActivity roomActivity;

    private SwipeRefreshLayout swipeRefreshLayout;

    private boolean setupCalled;

    public PlayListFragment ( ) { }

    public PlayListFragment ( Long id, RoomActivity roomActivity ) {
        this.currentRoom = id;
        this.roomActivity = roomActivity;
        setupCalled = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);

        setupCalled = false;

        list = new ArrayList<ListModelItem>();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new SongAdapter(list);

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Song selected " + mRecyclerView.getChildPosition(v), Toast.LENGTH_LONG).show();
            }
        });


        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        FloatingActionButton addSongButton = (FloatingActionButton) view.findViewById(R.id.add_song_button);
        addSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * Adds a new song
                * */
                startActivity(new Intent(roomActivity, AddNewSongActivity.class));
            }
        });

        return view;
    }

    void updateSongs(){
        while(!list.isEmpty()){
            list.remove(0);
            mAdapter.notifyDataSetChanged();
        }

        new GetRoomSongsAsyncTask(getActivity()){

            @Override
            public void onPreExecute(){}

            @Override
            public void onPostExecute(List<SongRoom> data){
                //super.onPostExecute(data);
                List<SongRoom> aux = data;
                Collections.sort(aux, new Comparator<SongRoom>() {
                    @Override
                    public int compare(SongRoom lhs, SongRoom rhs) {
                        return lhs.getIdxInQueue() - rhs.getIdxInQueue();
                    }
                });

                for(SongRoom sr : aux){

                    String cutSongName = sr.getSong().getSongName().length()
                            < 40?sr.getSong().getSongName():sr.getSong().getSongName().substring(0, 40);

                    String cutArtistName = sr.getSong().getArtist().length()
                            < 20?sr.getSong().getArtist():sr.getSong().getArtist().substring(0, 20);
                    Log.e("xd","name = "+ cutSongName );
                    list.add(new ListModelItem(cutSongName, sr.getSong().getSongYoutubeId(), sr.getSong().getArtist(),
                            sr.getSong().getThumbnailURL()));
                    mAdapter.notifyItemInserted(list.size());
                }


                swipeRefreshLayout.setRefreshing(false);

                if ( !setupCalled ) {
                    setupCalled = true;
                    if ( room.getRoomOwner().getId().equals(LoginActivity.currentUser.getId()) )
                        roomActivity.loadYoutubePlayerFragment();
                }
                tryToPlay();
            }
        }.execute(room.getId());
    }

    public void tryToPlay () {
        if ( youTubePlayer != null && !youTubePlayer.isPlaying() && !list.isEmpty() ) {
            youTubePlayer.loadVideo(list.get(0).getSongId());
        }
    }

    public void nextSong() {
        if (!list.isEmpty()) {
            list.remove(0);
            if ( !list.isEmpty() )
                youTubePlayer.loadVideo(list.get(0).getSongId());
        }
    }

    @Override
    public void onStart () {
        super.onStart();
        new GetRoomByIdAsyncTask( getActivity() ){
            @Override
            public void onPostExecute( Room theRoom ){
                super.onPostExecute(theRoom);
                roomActivity.room = room = theRoom;
                updateSongs();

                UserEnteredRoom userEnteredRoom = new UserEnteredRoom();
                userEnteredRoom.setRoom(room);
                userEnteredRoom.setUser(LoginActivity.currentUser);
                new InsertUserEnteredRoomAsyncTask(getActivity()){
                    @Override
                    public void onPostExecute(UserEnteredRoom data){
                        super.onPostExecute(data);
                        Log.d("HOLA", "Insertado UserEnteredRoom");
                    }
                }.execute(userEnteredRoom);
            }
        }.execute(currentRoom);
    }

    @Override
    public void onRefresh() {
        updateSongs();
    }

    public ArrayList<ListModelItem> getList() {
        return list;
    }

    public void setYouTubePlayer(YouTubePlayer youTubePlayer) {
        this.youTubePlayer = youTubePlayer;
    }

    public Room getRoom ( ) {
        return room;
    }
}
