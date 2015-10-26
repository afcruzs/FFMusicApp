package ffmusic.com.ffmusicapp.controller;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.model.RoomCollection;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetNearyByRoomsAsyncTask;

public class RoomsSearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_search_results);
        Log.d("HOLA", "On create");
        handleIntent(getIntent());

    }

    protected void onNewIntent (Intent intent){
        Log.d("HOLA","handling NEW intent...");
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            showResults(query);
        }
    }

    private void showResults(String query) {
        Log.d("HOLA", "This is my query " + query);
        final RoomsSearchResultsActivity aux = this;
        new GetNearyByRoomsAsyncTask(this){
            @Override
            public void onPostExecute(RoomCollection data){
                super.onPostExecute(data);

                Log.d("HOLA2",data.toString());
            }
        }.execute(LoginActivity.currentUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rooms_search_results, menu);
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
}
