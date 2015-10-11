package ffmusic.com.ffmusicapp.controller;

import android.util.Log;

import com.ffmusic.backend.ffMusicApi.model.User;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ffmusic.com.ffmusicapp.endpoints.InsertUserAsyncTask;

/**
 * Created by fabiankasUN on 10/10/2015.
 */
public class UserFactory {
    //"id,name,email,gender,birthday"
    public static final String FACEBOOK_NAME = "name";
    public static final String FACEBOOK_LASTNAME = "last_name";
    public static final String FACEBOOK_EMAIL = "email";

    public static User createUser (JSONObject fbProfile) {

        try {

            String firstName = fbProfile.getString(FACEBOOK_NAME);
            String lastName= fbProfile.getString(FACEBOOK_LASTNAME);
            String email = fbProfile.getString(FACEBOOK_EMAIL);
            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            return newUser;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User createUser( Person person, String email ) {
        User newUser = new User();

        newUser.setFirstName(person.getName().getGivenName());
        newUser.setLastName(person.getName().getFamilyName());
        newUser.setEmail(email);
        new InsertUserAsyncTask().execute(newUser);
        return newUser;
    }

    public static User getUser( String email ){

        return null;
        //new InsertUserAsyncTask().execute(xd);
    }
}
