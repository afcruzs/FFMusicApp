package ffmusic.com.ffmusicapp.controller;

import android.util.Log;

import com.ffmusic.backend.ffMusicApi.model.User;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ffmusic.com.ffmusicapp.endpoints.InsertUserAsyncTask;

public class UserFactory {
    //"id,name,email,gender,birthday"
    public static final String FACEBOOK_FIRST_NAME = "first_name";
    public static final String FACEBOOK_LASTNAME = "last_name";
    public static final String FACEBOOK_EMAIL = "email";

    public static User createUser (JSONObject fbProfile) {

        try {

            String firstName = fbProfile.getString(FACEBOOK_FIRST_NAME);
            String lastName= fbProfile.getString(FACEBOOK_LASTNAME);
            String email = fbProfile.getString(FACEBOOK_EMAIL);
            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            return newUser;

        } catch (JSONException e) {
            throw new RuntimeException("An error has ocurred putting data in the DataStore");
        }



    }

    public static User createUser( Person person, String email ) {
        User newUser = new User();
        newUser.setFirstName(person.getName().getGivenName());
        newUser.setLastName(person.getName().getFamilyName());
        newUser.setEmail(email);

        return newUser;
    }

}
