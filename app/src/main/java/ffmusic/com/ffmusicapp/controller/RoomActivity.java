package ffmusic.com.ffmusicapp.controller;

import android.support.design.widget.FloatingActionButton;
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
import com.ffmusic.backend.ffMusicApi.model.SongRoom;
import com.ffmusic.backend.ffMusicApi.model.SongRoomCollection;

import java.util.ArrayList;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetRoomByIdAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.GetRoomSongsAsyncTask;

public class RoomActivity extends AppCompatActivity {

    public static final String CURRENT_ROOM = "current_room";

    private ArrayList<ListModelItem> list;
    private int k = 0;

    private Room room;

    private RecyclerView mRecyclerView;
    private SongAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);


        list = new ArrayList<ListModelItem>();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new SongAdapter(list);

        //
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Pulsado el elemento " + mRecyclerView.getChildPosition(v), Toast.LENGTH_LONG).show();
            }
        });


        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(10));


        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        new GetRoomByIdAsyncTask( this ){
            @Override
            public void onPostExecute( Room theRoom ){
                super.onPostExecute(theRoom);

                room = theRoom;
                updateSongs();
                setUp();
            }
        }.execute(getIntent().getExtras().getLong(RoomActivity.CURRENT_ROOM));


    }

    void updateSongs(){
        new GetRoomSongsAsyncTask(this){
            @Override
            public void onPostExecute(SongRoomCollection data){
                super.onPostExecute(data);
                Log.d("hola", "INICIO");
                for(SongRoom sr : data.getItems()){
                    list.add(new ListModelItem(sr.getSong().getSongName()));
                    mAdapter.notifyItemInserted(list.size());
                    Log.d("hola",sr.getSong().getSongName());
                }
                Log.d("hola", "FIN");
            }
        }.execute(room.getId());
    }

    void setUp(){
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.create_new_room_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //for( int i = 0 ; i < list.size() ; i++ ){
                //    list.add(new ListModelItem("Song1"));
                //}
                list.add(new ListModelItem("Song" + k++));
                mAdapter.notifyItemInserted(list.size());
                //adapter.insert(new ListModelItem("Song1"), 0);
            }
        });
    }

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
}