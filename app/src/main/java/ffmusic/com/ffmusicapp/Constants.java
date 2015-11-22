package ffmusic.com.ffmusicapp;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.google.android.gms.common.Scopes;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.services.youtube.YouTubeScopes;

import java.io.IOException;


public class Constants {

    public static final String DEVELOPMENT_KEY = "AIzaSyB1f6PF0mzo6SvjVyoBqkOdMEqzQk26CYE";
    /*
    *   This is the ip of YOUR local machine if you want to test the backend locally.
    *   Please set it to run the backend in your machine.
    * */

    //public static final String DEVELOPMENT_IP = "192.168.1.21";
    public static final String DEVELOPMENT_IP = "192.168.1.4";
    //public static final String DEVELOPMENT_IP = " 10.203.163.108";

    public static final String DEVELOPMENT_ROOT_URL = "http://"+DEVELOPMENT_IP+":8080/_ah/api/";
    public static final String PRODUCTION_ROOT_URL = "https://ffmusicbackend.appspot.com/_ah/api/";

    /*
        If PRODUCTION_ROOT_URL the device will try to connect to the server in DEVELOPMENT_IP address.

        Otherwise it will connect to de api deployed in the google cloud.
    * */
    public static final String ROOT_URL = PRODUCTION_ROOT_URL;


    /*
    * Youtube connection constants
    *
    * */
    public static final int SEARCH_VIDEO = 1;

    public static final String SEARCH_VIDEO_MSG = "Searching Videos";

    public static final String DIALOG_TITLE = "Loading";

    public static final long NUMBER_OF_VIDEOS_RETURNED = 15;
    public static final String APP_NAME = "FFMusic";

    // Register an API key here: https://code.google.com/apis/console
    // Note : This is the browser key instead of android key as Android key was generating Service config errors (403)
    public static final String KEY = "AIzaSyDCpsDFxQ7ZTkClA4ArwqKk9FBgSqomjxM";
    public static final String[] SCOPES = {Scopes.PROFILE, YouTubeScopes.YOUTUBE};


    /*
        Returns the correct api builder according to the kind of ROOT_URL Choosen.
     */
    public static FfMusicApi.Builder getApiBuilder(){

        if( ROOT_URL.equals(DEVELOPMENT_ROOT_URL) ){
            FfMusicApi.Builder builder = new FfMusicApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(Constants.ROOT_URL).setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            return builder;
        }else{
            FfMusicApi.Builder builder = new FfMusicApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(Constants.ROOT_URL);

            return builder;
        }

    }
}
