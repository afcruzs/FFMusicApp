package ffmusic.com.ffmusicapp.controller;

import android.content.Intent;
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
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;

import java.util.Random;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetRoomsByUserAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.InsertRoomAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.InsertUserAsyncTask;

public class FFMusicMainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Fragment currentFragment;
    private Menu menu;

    /*
    * Options of NavigationView
    * */
    private final int NAV_HOME = R.id.nav_home;
    private final int NAV_YOUTUBE = R.id.nav_youtube;
    private final int NAV_SETTINGS = R.id.nav_settings;
    private final int NAV_LOG_OUT = R.id.nav_log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmusic_main);

        /*
        * Initializing Material Design components
        * */
        setUpNavigationView();
        setUpToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        /*
        * First time of execution HOME of app is showed
        * */
        if (savedInstanceState == null) {
            selectItem(NAV_HOME, getResources().getString(R.string.home));
        }
    }

    private void setUpNavigationView ( ) {
        TextView headerUsernameTextView = (TextView) findViewById(R.id.header_username);
        headerUsernameTextView.setText(LoginActivity.currentUser.getFullName());
        TextView headerEmailTextView = (TextView) findViewById(R.id.header_email);
        headerEmailTextView.setText(LoginActivity.currentUser.getEmail());
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marks item as checked
                        menuItem.setChecked(true);
                        // Selects new item on NavigationDrawer
                        selectItem(menuItem.getItemId(), menuItem.getTitle().toString());
                        return true;
                    }
                }
        );
    }

    /*
    * Manages the items on NavigationView
    *
    * Creates a new fragment to be replaced for the old one
    * This is done on NavigationDrawer structure
    * */
    private void selectItem(int itemId, String title) {
        Bundle args = new Bundle();
        Fragment newFragment;

        switch (itemId) {
            case NAV_HOME:
                newFragment = new HomeFragment();
                break;

            case NAV_YOUTUBE:
                Intent toYouTube = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                if ( toYouTube != null )
                    startActivity(toYouTube);
                else
                    Toast.makeText(this, R.string.youtube_app_not_found, Toast.LENGTH_SHORT);
                return;

            case NAV_SETTINGS:
                // Settings missing
                newFragment = new SettingsFragment();
                break;

            case NAV_LOG_OUT:
                // Log out missing
                return;

            default:
                newFragment = new HomeFragment();
                break;
        }

        newFragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();

        /*
        * Replaces new fragment over main_content layout
        * */
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_content, newFragment)
                .commit();

        drawerLayout.closeDrawers(); // Closes the drawer
        setTitle(title);

        currentFragment = newFragment;
    }


    /*
    * Missing menu options on NavigationDrawer
    * */
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
