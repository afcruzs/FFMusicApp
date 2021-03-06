package com.example.test;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
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

import com.ffmusic.youtubeconnection.VideoItem;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_results);
        handler = new Handler();


        list = new ArrayList<VideoItem>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_youtube_searches);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new YoutubeItemAdapter(list, this);

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("xd", "listener act");

                Toast.makeText(getBaseContext(), "Song selected " + mRecyclerView.getChildPosition(v), Toast.LENGTH_LONG).show();
            }
        });


        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(10));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }

    /*
    * Searches on youtube and then calls updateVideosFound to show videos
    * */
    private void searchOnYoutube(final String keywords) {
        new AsyncTask<String, Void, List<VideoItem>>() {
            @Override
            protected List<VideoItem> doInBackground(String... params) {
                YoutubeConnector yc = new YoutubeConnector(getString(R.string.app_name));
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
        list.clear();
        for(VideoItem sr : searchResults){
            list.add(sr);
            mAdapter.notifyItemInserted(list.size());
        }

        mAdapter.notifyItemInserted(list.size());
    }

}

