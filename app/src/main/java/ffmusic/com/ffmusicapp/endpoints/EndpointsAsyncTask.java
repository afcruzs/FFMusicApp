package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.User;
import com.ffmusic.backend.myApi.MyApi;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by PC on 27/09/2015.
 */

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private static FfMusicApi ffMusicApi = null;
    private Context context;


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://ffmusicbackend.appspot.com/_ah/api/");

            myApiService = builder.build();


            FfMusicApi.Builder builder2 = new FfMusicApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://ffmusicbackend.appspot.com/_ah/api/");

            ffMusicApi = builder2.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            User xd = new User();
            xd.setFirstName("asdsdasad");
            xd.setLastName("vvvxxxsavv");
            xd.setEmail("vsadasdsad");
          //  ffMusicApi.userEndPoint().insertUser(xd).execute();
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}