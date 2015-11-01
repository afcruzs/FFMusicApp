package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.Song;
import com.ffmusic.backend.ffMusicApi.model.SongRoom;
import com.ffmusic.backend.ffMusicApi.model.SongRoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;
import com.ffmusic.backend.ffMusicApi.model.Room;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.RadioButton;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetRoomSongsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.InsertRoomAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.SaveSongAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.SaveSongRoom;

public class CreateRoomActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int OK = 0;
    public static final int EMPTY_FIELDS = 1;
    public static final int PASSWORDS_DO_NOT_MATCH = 2;
    public static final String PUBLIC_PASSWORD = "-";

    private EditText textPassword;
    private EditText textPasswordValidate;
    private EditText textName;
    private CheckBox buttom_private;
    private TextInputLayout inputLayoutPasswordRoom;
    private TextInputLayout inputLayoutPasswordValidateRoom;
    private Button button_create;
    private TextInputLayout inputLayoutNameRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        findViewById(R.id.room_create_button).setOnClickListener(this);

        textName = (EditText) findViewById(R.id.input_name_room);
        textPassword = (EditText) findViewById(R.id.input_password_room);
        textPasswordValidate = (EditText) findViewById(R.id.input_password_validate);
        buttom_private = (CheckBox) findViewById(R.id.buttom_private);
        inputLayoutPasswordRoom = (TextInputLayout) findViewById(R.id.input_layout_password_room);
        inputLayoutPasswordValidateRoom = (TextInputLayout) findViewById(R.id.input_layout_password_validate_room);
        button_create = (Button) findViewById(R.id.room_create_button);
        inputLayoutNameRoom = (TextInputLayout) findViewById(R.id.input_layout_name_room);

        textPassword.setVisibility(View.INVISIBLE);
        textPasswordValidate.setVisibility(View.INVISIBLE);
        inputLayoutPasswordRoom.setVisibility(View.INVISIBLE);
        inputLayoutPasswordValidateRoom.setVisibility(View.INVISIBLE);

        buttom_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttom_private.isChecked()) {

                    visible();
                } else {
                    invisible();
                }
            }
        });
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

    public void visible(){
        textPassword.setVisibility(View.VISIBLE);
        textPasswordValidate.setVisibility(View.VISIBLE);
        inputLayoutPasswordRoom.setVisibility(View.VISIBLE);
        inputLayoutPasswordValidateRoom.setVisibility(View.VISIBLE);
    }

    public void invisible(){
        textPassword.setVisibility(View.INVISIBLE);
        textPasswordValidate.setVisibility(View.INVISIBLE);
        inputLayoutPasswordRoom.setVisibility(View.INVISIBLE);
        inputLayoutPasswordValidateRoom.setVisibility(View.INVISIBLE);
    }


    private int validate(String name, String pass, String vPass) {
        if (name.equals("") || pass.equals("") || vPass.equals("")) {
            if( !buttom_private.isChecked() && !name.equals(""))
                return OK;
            return EMPTY_FIELDS;
        }
        if (!pass.equals(vPass)) {
            return PASSWORDS_DO_NOT_MATCH;
        }

        return OK;
    }

    private Room auxRoom;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.room_create_button:
                String name = textName.getText().toString();
                String password = textPassword.getText().toString();
                String passwordValidate = textPasswordValidate.getText().toString();
                if ( !buttom_private.isChecked() ) {
                    password = PUBLIC_PASSWORD;
                    passwordValidate = PUBLIC_PASSWORD;
                }

                int result = validate(name, password, passwordValidate);
                switch (result) {
                    case OK:
                        Room r = new Room();
                        r.setName(name);
                        r.setPassword(password);
                        r.setRoomOwner(LoginActivity.currentUser);

                        // Test
                        new InsertRoomAsyncTask(this) {
                            @Override
                            public void onPostExecute(Room room) {
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
                                for (int l = 0; l < 3; ++l) {
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

                        }.execute(r);
                        break;
                    case EMPTY_FIELDS:
                        inputLayoutNameError();
                        inputLayoutPasswordError();
                        inputLayoutValidateError();
                        Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
                        break;
                    case PASSWORDS_DO_NOT_MATCH:
                        inputLayoutNameError();
                        inputLayoutPasswordError();
                        inputLayoutValidateError();
                        Toast.makeText(this, R.string.passwords_do_not_match, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(this, R.string.unknown_error, Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void inputLayoutNameError() {
        if (textName.getText().toString().equals("")) {
            inputLayoutNameRoom.setErrorEnabled(true);
            inputLayoutNameRoom.setError(getString(R.string.error_name));
            //inputLayoutNameRoom.getBackground().setColorFilter(getResources().getColor(R.color.gray_color), PorterDuff.Mode.SRC_ATOP);
        } else {
            inputLayoutNameRoom.setErrorEnabled(false);
            inputLayoutNameRoom.setError("");
            //inputLayoutNameRoom.getBackground().setColorFilter(getResources().getColor(R.color.red_color), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public void inputLayoutPasswordError() {
        if (textPassword.getText().toString().equals("")) {
            inputLayoutPasswordRoom.setErrorEnabled(true);
            //inputLayoutPasswordRoom.getBackground().setColorFilter(getResources().getColor(R.color.gray_color), PorterDuff.Mode.SRC_ATOP);
            inputLayoutPasswordRoom.setError(getString(R.string.error_password_message));
        } else {
            inputLayoutPasswordRoom.setErrorEnabled(false);
            inputLayoutPasswordRoom.setError("");
           // inputLayoutPasswordRoom.getBackground().setColorFilter(getResources().getColor(R.color.red_color), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public void inputLayoutValidateError() {
        if (textPasswordValidate.getText().toString().equals("")) {
            inputLayoutPasswordValidateRoom.setErrorEnabled(true);
            //inputLayoutPasswordValidateRoom.getBackground().setColorFilter(getResources().getColor(R.color.gray_color), PorterDuff.Mode.SRC_ATOP);
            inputLayoutPasswordValidateRoom.setError(getString(R.string.error_password_message_validate));
        } else {
            inputLayoutPasswordValidateRoom.setErrorEnabled(false);
            inputLayoutPasswordValidateRoom.setError("");
            //inputLayoutPasswordValidateRoom.getBackground().setColorFilter(getResources().getColor(R.color.red_color), PorterDuff.Mode.SRC_ATOP);
        }

    }


    void onRoomCreated() {
        Toast.makeText(getApplicationContext(), R.string.room_created, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), FFMusicMainActivity.class));
    }
}
