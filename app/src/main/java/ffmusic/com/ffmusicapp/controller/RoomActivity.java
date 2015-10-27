package ffmusic.com.ffmusicapp.controller;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.Song;
import com.ffmusic.backend.ffMusicApi.model.SongRoom;
import com.ffmusic.backend.ffMusicApi.model.SongRoomCollection;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.Constants;
import ffmusic.com.ffmusicapp.endpoints.GetRoomByIdAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.GetRoomSongsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.SaveSongAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.SaveSongRoom;

public class RoomActivity extends AppCompatActivity {

    public static final String CURRENT_ROOM = "current_room";

    private ArrayList<ListModelItem> list;
    private int k = 0;

    private Room room;

    private RecyclerView mRecyclerView;
    private SongAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private YouTubePlayer youTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);


        list = new ArrayList<ListModelItem>();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new SongAdapter(list);

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Song selected " + mRecyclerView.getChildPosition(v), Toast.LENGTH_LONG).show();
            }
        });


        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(10));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    void updateSongs(){
        new GetRoomSongsAsyncTask(this){
            @Override
            public void onPostExecute(SongRoomCollection data){
                super.onPostExecute(data);
                for(SongRoom sr : data.getItems()){
                    list.add(new ListModelItem(sr.getSong().getSongName(), sr.getSong().getSongYoutubeId(), sr.getSong().getArtist()));
                    mAdapter.notifyItemInserted(list.size());
                }
                setUp();
            }
        }.execute(room.getId());
    }

    /*
    * Loads components of a single room
    * */
    void setUp ( ) {
        /*
        * If the owner of this room is the user who is managing the application
        * YouTubePlayer must be loaded
        * */
        if (room.getRoomOwner().getId().equals(LoginActivity.currentUser.getId()))
            loadYoutubePlayerFragment();

        FloatingActionButton addSongButton = (FloatingActionButton) findViewById(R.id.add_song_button);
        addSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * Adds a new song
                * */

                final Song song = new Song();
                song.setSongName("The man who sold the world");
                song.setSongYoutubeId("fregObNcHC8");
                song.setArtist("Nirvana");

                new SaveSongAsyncTask(RoomActivity.this) {
                    @Override
                    public void onPostExecute(Song realSong) {
                        super.onPostExecute(realSong);

                        final SongRoom songRoom = new SongRoom();
                        songRoom.setCreatedBy(LoginActivity.currentUser);
                        songRoom.setIdxInQueue(k++);
                        songRoom.setSong(realSong);
                        songRoom.setRoom(room);
                        new SaveSongRoom(context) {
                            @Override
                            public void onPostExecute(SongRoom sr) {
                                super.onPostExecute(sr);
                                list.add(new ListModelItem(song.getSongName(), song.getSongYoutubeId(), song.getArtist()));
                                mAdapter.notifyItemInserted(list.size());
                            }
                        }.execute(songRoom);

                    }
                }.execute(song);
            }
        });
    }

    private void loadYoutubePlayerFragment() {
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        youTubePlayerFragment.initialize(Constants.DEVELOPMENT_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean restored) {
                setYouTubePlayer(youTubePlayer);
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {

                    }

                    @Override
                    public void onLoaded(String s) {

                    }

                    @Override
                    public void onAdStarted() {

                    }

                    @Override
                    public void onVideoStarted() {

                    }

                    /*
                    * When current song finishes next song must be loaded
                    * */
                    @Override
                    public void onVideoEnded() {
                        if (!list.isEmpty()) {
                            list.add(list.remove(0));
                            youTubePlayer.loadVideo(list.get(0).getSongId());
                        }
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });


                /*
                * The first song begins to play
                * */
                if ( !list.isEmpty() )
                    youTubePlayer.loadVideo(list.get(0).getSongId());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragManager.beginTransaction();

        fragmentTransaction.replace(R.id.youtube_player, youTubePlayerFragment);
        fragmentTransaction.commit();
    }

    /*
    * Missing menu options
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public YouTubePlayer getYouTubePlayer() {
        return youTubePlayer;
    }

    public void setYouTubePlayer(YouTubePlayer youTubePlayer) {
        this.youTubePlayer = youTubePlayer;
    }

    @Override
    public void onStart () {
        super.onStart();
        new GetRoomByIdAsyncTask( this ){
            @Override
            public void onPostExecute( Room theRoom ){
                super.onPostExecute(theRoom);
                room = theRoom;
                updateSongs();
            }
        }.execute(getIntent().getExtras().getLong(RoomActivity.CURRENT_ROOM));
    }
}