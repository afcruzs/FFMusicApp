package com.example.test;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ffmusic.youtubeconnection.VideoItem;
import com.ffmusic.youtubeconnection.YoutubeConnector;

import java.util.List;

public class YoutubeResultsActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_youtube_results, menu);
        handler = new Handler();
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
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchOnYoutube(query);
        }
    }

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



    private void updateVideosFound(List<VideoItem> searchResults) {
        Log.d("xd", searchResults.get(0).getTitle() + "");
    }

}

