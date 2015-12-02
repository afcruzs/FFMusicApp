package ffmusic.com.ffmusicapp.controller;

import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;
import java.util.Stack;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.util.ErrorHandlerUI;

public class FFMusicMainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Fragment currentFragment;
    private Menu menu;
    private Stack<Fragment> fragmentStack;

    /*
    * Options of NavigationView
    * */
    private final int NAV_HOME = R.id.nav_home;
    private final int NAV_YOUTUBE = R.id.nav_youtube;
    //private final int NAV_SETTINGS = R.id.nav_settings;
    private final int NAV_LOG_OUT = R.id.nav_log_out;
    private final String SETTINGS_TAG = R.string.setting_tag+"";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmusic_main);

        fragmentStack = new Stack<>();
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
        try {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
            TextView headerUsernameTextView = (TextView) headerView.findViewById(R.id.header_username);
            headerUsernameTextView.setText(LoginActivity.currentUser.getFullName());
            TextView headerEmailTextView = (TextView) headerView.findViewById(R.id.header_email);
            headerEmailTextView.setText(LoginActivity.currentUser.getEmail());
        }catch(Exception e){
            ErrorHandlerUI.showError(this,e);
        }
    }




    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if ( toolbar != null ) {
            setSupportActionBar(toolbar);
            final ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setHomeAsUpIndicator(R.drawable.ic_menu);
                ab.setDisplayHomeAsUpEnabled(true);
            }
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
/*
            case NAV_SETTINGS:
                newFragment = new SettingsFragment();

                break;
*/
            case NAV_LOG_OUT:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.log_out_title);
                builder.setMessage(R.string.log_out_message);
                builder.setPositiveButton(R.string.yes_message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(FFMusicMainActivity.this);
                        SharedPreferences.Editor ed = mPrefs.edit();
                        ed.clear();
                        ed.commit();
                        try {
                            Runtime runtime = Runtime.getRuntime();
                            runtime.exec("pm clear ffmusic.com.ffmusicapp");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                builder.setNegativeButton(R.string.no_message, null);
                builder.show();
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
                .replace(R.id.main_content, newFragment).commit();


        drawerLayout.closeDrawers(); // Closes the drawer
        setTitle(title);

        currentFragment = newFragment;
        fragmentStack.push(newFragment);
    }


    /*
    * Missing menu options on NavigationDrawer
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ffmusic_main, menu);

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_rooms_search_results,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


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


    public void removeCurrentFragment()
    {
        FragmentTransaction tx = getFragmentManager().beginTransaction();

        Fragment currentFrag =  getSupportFragmentManager().findFragmentById(currentFragment.getId());
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if( fragmentStack.size() > 1 ){
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentStack.lastElement().onPause();
                fragmentStack.pop();
                fragmentStack.lastElement().onResume();
                fragmentManager.beginTransaction().replace(R.id.main_content, fragmentStack.lastElement()).commit();
                return true;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
