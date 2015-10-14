package ffmusic.com.ffmusicapp.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.model.User;
import com.ffmusic.backend.ffMusicApi.model.Room;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.InsertRoomAsyncTask;

public class CreateRoomActivity extends AppCompatActivity implements View.OnClickListener {



    public static final String OK = "OK";
    public static final String EMPTY_FIELDS = "Empty fields";
    public static final String PASSWORDS_DO_NOT_MATCH = "passwords do not match";


    private EditText textPassword;
    private EditText textPasswordValidate;
    private AutoCompleteTextView textName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        findViewById(R.id.buttom_accept).setOnClickListener(this);

        textName = (AutoCompleteTextView)findViewById(R.id.name_room);
        textPasswordValidate = (EditText) findViewById(R.id.passwordValidate);
        textPassword = (EditText) findViewById(R.id.password);
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

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttom_accept:
                String name = textName.getText().toString();
                String password = textPassword.getText().toString();
                String passwordValidate = textPasswordValidate.getText().toString();
                String result = validate(name, password, passwordValidate);
                if( result.equals(OK) ){
                    Room r = new Room();
                    r.setName( name );
                    r.setPassword(password);
                    r.setRoomOwner( LoginActivity.currentUser );
                    new InsertRoomAsyncTask(){
                        @Override
                        public void onPostExecute(Room room){
                            Toast.makeText(getApplicationContext(), "Room Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), FFMusicMainActivity.class));
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

}
