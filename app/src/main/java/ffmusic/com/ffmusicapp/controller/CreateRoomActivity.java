package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.Song;
import com.ffmusic.backend.ffMusicApi.model.SongRoom;
import com.ffmusic.backend.ffMusicApi.model.SongRoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;
import com.ffmusic.backend.ffMusicApi.model.Room;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetRoomSongsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.InsertRoomAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.SaveSongAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.SaveSongRoom;

public class    CreateRoomActivity extends AppCompatActivity implements View.OnClickListener {



    public static final String OK = "OK";
    public static final String EMPTY_FIELDS = "Empty fields";
    public static final String PASSWORDS_DO_NOT_MATCH = "passwords do not match";


    private TextInputLayout textPasswordLayout;
    private TextInputLayout textPasswordValidateLayout;
    private TextInputLayout textNameLayout;

    private EditText textPassword;
    private EditText textPasswordValidate;
    private EditText textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        findViewById(R.id.room_create_buttom).setOnClickListener(this);

        textNameLayout = (TextInputLayout)findViewById(R.id.input_layout_name_room);
        textPasswordValidateLayout = (TextInputLayout) findViewById(R.id.input_layout_password_validate_room);
        textPasswordLayout = (TextInputLayout) findViewById(R.id.input_layout_password_room);

        textName = (EditText)findViewById(R.id.input_name_room);
        textPassword = (EditText)findViewById(R.id.input_password_room);
        textPasswordValidate = (EditText)findViewById(R.id.input_password_validate);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_room, menu);
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


    private String validate( String name, String pass, String vPass){
        if( name.equals("") || pass.equals("") || vPass.equals("")){
            return EMPTY_FIELDS;
        }
        if(!pass.equals(vPass)){
            return PASSWORDS_DO_NOT_MATCH;
        }

        return OK;
    }
    Room auxRoom;
    @Override
    public void onClick(View view){
        Context context = this;
        switch (view.getId()){
            case R.id.room_create_buttom:
                String name = textName.getText().toString();
                String password = textPassword.getText().toString();
                String passwordValidate = textPasswordValidate.getText().toString();
                String result = validate(name, password, passwordValidate);
                if( result.equals(OK) ){
                    Room r = new Room();
                    r.setName( name );
                    r.setPassword(password);
                    r.setRoomOwner( LoginActivity.currentUser );
                    new InsertRoomAsyncTask(this){
                        @Override
                        public void onPostExecute(Room room){
                            super.onPostExecute(room);
                            //Test code!
                            Song theSongs[] = new Song[3];
                            theSongs[0] = new Song();
                            theSongs[0].setSongName("Someday");
                            theSongs[0].setArtist("The Strokes");
                            theSongs[0].setSongYoutubeId("knU9gRUWCno");
                            theSongs[1] = new Song();
                            theSongs[1].setSongName("Fluorescent Adolescent");
                            theSongs[1].setArtist("AM");
                            theSongs[1].setSongYoutubeId("ma9I9VBKPiw");
                            theSongs[2] = new Song();
                            theSongs[2].setSongName("Resistance");
                            theSongs[2].setArtist("Muse");
                            theSongs[2].setSongYoutubeId("TPE9uSFFxrI");

                            auxRoom = room;
                            for ( int l = 0; l < 3; ++l  ) {
                                new SaveSongAsyncTask(context) {
                                    @Override
                                    public void onPostExecute(final Song realSong) {
                                        super.onPostExecute(realSong);
                                        SongRoom songRoom = new SongRoom();
                                        songRoom.setRoom(auxRoom);
                                        songRoom.setSong(realSong);
                                        songRoom.setIdxInQueue(new Integer(0));
                                        songRoom.setCreatedBy(LoginActivity.currentUser);
                                        new SaveSongRoom(context) {
                                            @Override
                                            public void onPostExecute(SongRoom realSongRoom) {
                                                super.onPostExecute(realSongRoom);
                                                new GetRoomSongsAsyncTask(context) {
                                                    @Override
                                                    public void onPostExecute(SongRoomCollection data) {
                                                        super.onPostExecute(data);
                                                        Log.d("hola", "INICIO");
                                                        //Log.d("hola",auxRoom.getName() + " "  + auxRoom.getId() + " " + auxRoom.getRoomOwner() + " " + auxRoom.getPassword()  );
                                                        for (SongRoom sr : data.getItems()) {
                                                            Log.d("hola", sr.getSong().getSongName());
                                                        }
                                                        Log.d("hola", "FIN");
                                                        onRoomCreated();
                                                    }
                                                }.execute(auxRoom.getId());
                                            }
                                        }.execute(songRoom);
                                    }
                                }.execute(theSongs[l]);
                            }
                        }

                    }.execute( r );

                }else if( result.equals(EMPTY_FIELDS) ){
                    Toast.makeText(this,EMPTY_FIELDS, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,PASSWORDS_DO_NOT_MATCH, Toast.LENGTH_SHORT).show();
                }



                break;
        }
    }

    void onRoomCreated(){
        Toast.makeText(getApplicationContext(), "Room Created " , Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), FFMusicMainActivity.class));
    }

}
