package ffmusic.com.ffmusicapp.controller;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.util.Constants;

public class RoomActivity extends AppCompatActivity {

    public static final String CURRENT_ROOM = "current_room";
    public static final String CURRENT_ROOM_NAME = "current_room_name";

    public static Room room;
    private PlayListFragment playlistFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        setTitle(getIntent().getExtras().getString(CURRENT_ROOM_NAME));
        setUpToolbar();
        setUp();
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if ( toolbar != null ) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    /*
    * Loads components of a single room
    * */
    void setUp ( ) {
        loadPlaylist();
    }

    private void loadPlaylist ( ) {
        playlistFragment = PlayListFragment.newInstance(getIntent().getExtras().getLong(RoomActivity.CURRENT_ROOM), this);

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragManager.beginTransaction();

        fragmentTransaction.replace(R.id.playlist, playlistFragment);
        fragmentTransaction.commit();
    }


    public void loadYoutubePlayerFragment() {
        final YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        youTubePlayerFragment.initialize(Constants.DEVELOPMENT_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean restored) {
                playlistFragment.setYouTubePlayer(youTubePlayer);
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
                        playlistFragment.nextSong();
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });
                playlistFragment.tryToPlay();
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
        switch( id ) {
            case R.id.delete_room:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}