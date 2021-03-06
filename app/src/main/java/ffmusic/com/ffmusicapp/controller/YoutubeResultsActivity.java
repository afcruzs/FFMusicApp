package ffmusic.com.ffmusicapp.controller;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.SongRoom;

import java.util.ArrayList;
import java.util.List;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.util.ErrorHandlerUI;
import ffmusic.com.ffmusicapp.util.NoResults;
import ffmusic.com.ffmusicapp.youtube_connection.VideoItem;
import ffmusic.com.ffmusicapp.youtube_connection.YoutubeConnector;

public class YoutubeResultsActivity extends AppCompatActivity {

    private Handler handler;
    private ArrayList<VideoItem> list;

    private RecyclerView mRecyclerView;
    private YoutubeItemAdapter mAdapter;
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_youtube_results, menu);
        return true;
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if ( toolbar != null ) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_results);

        setUpToolbar();
        handler = new Handler();


        list = new ArrayList<VideoItem>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_youtube_searches);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new YoutubeItemAdapter(list, this);

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("xd", "listener act");

                Toast.makeText(getBaseContext(), "Song selzzzzected " + mRecyclerView.getChildPosition(v), Toast.LENGTH_LONG).show();
            }
        });


        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }

    /*
    * Searches on youtube and then calls updateVideosFound to show results
    * */
    private void searchOnYoutube(final String keywords) {
        new AsyncTask<String, Void, List<VideoItem>>() {
            @Override
            protected List<VideoItem> doInBackground(String... params) {
                YoutubeConnector yc = new YoutubeConnector();
                return yc.search(keywords);
            }
            @Override
            protected void onPostExecute (List<VideoItem> videos) {
                updateVideosFound(videos);
            }
        }.execute(keywords);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchOnYoutube(query);
        }
    }

    private void updateVideosFound(List<VideoItem> searchResults) {
        if( searchResults == null ){
            ErrorHandlerUI.showError(this, getResources().getString(R.string.no_conexion_error) );
            return;
        }
        if ( searchResults.isEmpty() ) NoResults.show(this);
        else NoResults.hide(this);
        list.clear();
        for(VideoItem sr : searchResults){
            list.add(sr);
            mAdapter.notifyItemInserted(list.size());
        }

        mAdapter.notifyItemInserted(list.size());
    }

}

