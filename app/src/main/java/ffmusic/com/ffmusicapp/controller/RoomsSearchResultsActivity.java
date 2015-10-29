package ffmusic.com.ffmusicapp.controller;

import android.app.SearchManager;
import android.content.Intent;
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
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;

import java.util.ArrayList;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetNearyByRoomsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.GetRoomsByPrefixAsyncTask;

public class RoomsSearchResultsActivity extends AppCompatActivity {

    private ArrayList<RoomListModelItem> list;

    private RecyclerView mRecyclerView;
    private RoomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_search_results);
        Log.d("HOLA", "On create");
        list = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RoomAdapter(list);

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Rooom selected " + mRecyclerView.getChildPosition(v), Toast.LENGTH_LONG).show();
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
        new GetRoomsByPrefixAsyncTask(this){
            @Override
            public void onPostExecute(RoomCollection data){
                super.onPostExecute(data);
                for( Room room : data.getItems() ){
                    list.add( new RoomListModelItem( room.getName(), room.getRoomOwner().getFirstName() ) );
                    mAdapter.notifyItemInserted(list.size());
                }
                Log.d("HOLA2",data.toString());
            }
        }.execute(query);
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
