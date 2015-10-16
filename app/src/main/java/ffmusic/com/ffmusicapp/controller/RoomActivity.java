package ffmusic.com.ffmusicapp.controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.model.Room;

import java.util.ArrayList;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetRoomByIdAsyncTask;

public class RoomActivity extends AppCompatActivity {

    public static final String CURRENT_ROOM = "current_room";
    private ListAdapter adapter;
    private Room room;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        setupListViewAdapter();

        new GetRoomByIdAsyncTask( this ){
            @Override
            public void onPostExecute( Room theRoom ){
                super.onPostExecute(theRoom);

                room = theRoom;
                setUp();
            }
        }.execute( getIntent().getExtras().getLong(RoomActivity.CURRENT_ROOM) );


    }

    void setUp(){
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.create_new_room_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.insert(new ListModelItem("Song1"), 0);
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


    private void setupListViewAdapter() {
        adapter = new ListAdapter(RoomActivity.this, R.layout.room_play_list_item, new ArrayList<ListModelItem>());
        ListView atomPaysListView = (ListView)findViewById(R.id.room_list);
        atomPaysListView.setAdapter(adapter);
    }

}
