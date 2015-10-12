package ffmusic.com.ffmusicapp.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.TextView;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetRoomsByUserAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.InsertRoomAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.InsertUserAsyncTask;

public class FFMusicMainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Fragment currentFragment;
    private Menu menu;

    private final int NAV_HOME = R.id.nav_home;
    private final int NAV_ANOTHER_ITEM = R.id.nav_another_item;
    private final int NAV_LOG_OUT = R.id.nav_log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmusic_main);

        initNavigationView();
        setToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        if (savedInstanceState == null) {
            selectItem(NAV_HOME, getResources().getString(R.string.home));
        }
    }

    private void initNavigationView ( ) {
        TextView headerUsernameTextView = (TextView) findViewById(R.id.header_username);
        headerUsernameTextView.setText(LoginActivity.currentUser.getFullName());
        TextView headerEmailTextView = (TextView) findViewById(R.id.header_email);
        headerEmailTextView.setText(LoginActivity.currentUser.getEmail());
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onClickUserTest(View view){
        insertUserTest();
    }


    public void insertUserTest() {
        Log.d("Pecora", "HOLA");
        /*
        for (int i = 0; i < 5; i++){
            Room room = new Room();
            room.setName("Nombre room test"+i);
            room.setPassword("pecoraUN"+i);

            room.setRoomOwner(LoginActivity.currentUser);

            new InsertRoomAsyncTask() {
                @Override
                public void onPostExecute(Room room) {
                    Log.d("LEL", "Funciono perri " + room);
                }
            }.execute(room);
        }

        */


        /*
        new GetRoomsByUserAsyncTask(){
            @Override
            public void onPostExecute(RoomCollection result){
                Log.d("Rooms","INICIO ROOMS");
                for(Room room : result.getItems()){
                    Log.d("Rooms",room.toString());
                }
                Log.d("Rooms","FIN ROOMS");
            }
        }.execute(LoginActivity.currentUser);

        */

    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marks item
                        menuItem.setChecked(true);
                        // Creates a new fragment
                        selectItem(menuItem.getItemId(), menuItem.getTitle().toString());
                        return true;
                    }
                }
        );
    }

    private void selectItem(int itemId, String title) {
        Bundle args = new Bundle();
        Fragment newFragment;

        switch (itemId) {
            case NAV_HOME:
                args.putString(HomeFragment.TITLE, title);
                newFragment = new HomeFragment();
                break;

            case NAV_ANOTHER_ITEM:

            case NAV_LOG_OUT:


            default:
                args.putString(HomeFragment.TITLE, title);
                newFragment = new HomeFragment();
                break;
        }

        newFragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager
                .beginTransaction()
                .replace(R.id.main_content, newFragment)
                .commit();

        drawerLayout.closeDrawers(); // Closes the drawer
        setTitle(title);

        currentFragment = newFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ffmusic_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
