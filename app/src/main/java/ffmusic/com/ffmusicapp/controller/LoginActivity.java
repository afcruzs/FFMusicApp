package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONObject;


import java.util.List;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetNearyByRoomsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.GetRoomsByUserAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.GetUserByEmailAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.InsertUserAsyncTask;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    public static User currentUser;
    public static final String IS_LOGGED = "is_logged";
    public static final String EMAIL = "email";
    private SharedPreferences mPrefs;
    private boolean isLogged;
    private String emailUser;

    /*
    * Facebook Login
    * */

    private LoginButton loginFacebookButton;
    private CallbackManager callbackManager;

    private void facebookLogin() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        loginFacebookButton = (LoginButton)findViewById(R.
                id.login_button);

        loginFacebookButton.setReadPermissions("public_profile");
        loginFacebookButton.setReadPermissions("email");
        final Context aux = this;
        loginFacebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {

                                //currentUser = UserFactory.createUser(object);
                                new InsertUserAsyncTask(aux){
                                    @Override
                                    public void onPostExecute(User user){
                                        super.onPostExecute(user);
                                        currentUser = user;
                                        startMainActivity();
                                    }
                                }.execute( UserFactory.createUser(object) );

                                isLogged = true;
                                try {
                                    emailUser = object.getString(UserFactory.FACEBOOK_EMAIL);

                                }catch( Exception e){
                                    Log.d(TAG,e+"");
                                }

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", UserFactory.FACEBOOK_EMAIL +"," + UserFactory.FACEBOOK_FIRST_NAME +"," +
                        UserFactory.FACEBOOK_LASTNAME);
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {
                Log.d(TAG, e.getMessage() + " ");
            }
        });
    }

    /*
    *   Google login
    * */

    private GoogleApiClient mGoogleApiClient;
    private TextView status;

    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;

    private boolean isConnect = false;

    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 1;
    private static final String KEY_IS_RESOLVING = "is_resolving";
    private static final String KEY_SHOULD_RESOLVE = "should_resolve";



    protected void setGooglePlusButtonText(SignInButton signInButton, String buttonText) {
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setTextSize(15);
                tv.setTypeface(null, Typeface.NORMAL);
                tv.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                tv.setText(buttonText);
                return;
            }
        }
    }

    private void googleLogin () {
        findViewById(R.id.sign_in_button).setOnClickListener(this);


        ((SignInButton)findViewById(R.id.sign_in_button)).setSize(SignInButton.SIZE_WIDE);
        SignInButton gPlusLoginButton = (SignInButton) findViewById(R.id.sign_in_button);
        setGooglePlusButtonText(gPlusLoginButton,"Sign in with Google");


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .addScope(new Scope(Scopes.EMAIL))
                .build();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        if ( mPrefs.getBoolean(IS_LOGGED, false) ) {

            isLogged = true;
            emailUser = mPrefs.getString(EMAIL,null);
            //currentUser = UserFactory.getUser(emailUser);
            new GetUserByEmailAsyncTask(this){
             @Override
             public void onPostExecute(User user){
                 super.onPostExecute(user);
                 if(user == null) throw new RuntimeException("THE USER IS NULLLL " + emailUser );
                 currentUser = user;
                 Log.d(TAG, "Cargado del user ");
                 startMainActivity();
             }
            }.execute(emailUser);


            return;
        }else{
            facebookLogin();
            googleLogin();
        }
    }


    public void startMainActivity() {

        final LoginActivity holder = this;
        new GetRoomsByUserAsyncTask(this){
            @Override
            public void onPostExecute(List<Room> result){
                RoomsFragment.setUserRooms(result);

                new GetNearyByRoomsAsyncTask(holder){
                    @Override
                    public void onPostExecute(List<Room> rooms){
                        super.onPostExecute(rooms);
                        RoomsFragment.setOtherRooms(rooms);
                        Intent intent = new Intent( holder, FFMusicMainActivity.class );
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }.execute(LoginActivity.currentUser);


            }
        }.execute(LoginActivity.currentUser);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();
        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume ( ) {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putBoolean(IS_LOGGED, currentUser != null);
        ed.putString(EMAIL, emailUser);
        ed.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //mGoogleApiClient.disconnect();
        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putBoolean(IS_LOGGED, isLogged );
        ed.putString(EMAIL, emailUser);
        ed.commit();
    }


    public void update(){
        Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        final String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
        if (currentPerson != null) {
            new InsertUserAsyncTask(this){
                @Override
                public void onPostExecute(User result) {
                    super.onPostExecute(result);
                    currentUser = result;
                    isLogged = true;
                    emailUser = email;
                    startMainActivity();

                }
            }.execute( UserFactory.createUser(currentPerson,email) );
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected:" + bundle);
        mShouldResolve = false;
        update();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button:
                    onSignInClicked();
                break;


        }
    }

    // [START on_sign_out_clicked]
    private void onSignOutClicked() {
        // Clear the default account so that GoogleApiClient will not automatically
        // connect in the future.
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            startMainActivity();
        }
    }

    // [START on_disconnect_clicked]
    private void onDisconnectClicked() {
        // Revoke all granted permissions and clear the default account.  The user will have
        // to pass the consent screen to sign in again.
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            startMainActivity();
        }
    }


    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (!mIsResolving && mShouldResolve) {

            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            }

        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}
