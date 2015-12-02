package ffmusic.com.ffmusicapp.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.net.UnknownHostException;

import ffmusic.com.ffmusicapp.R;

/**
 * Created by PC on 30/11/2015.
 */
public class ErrorHandlerUI {
    public static void  showError(Context context, Exception theError){
        Log.d("error",theError.toString());
        String message = context.getResources().getString(R.string.unknown_error);
        //message = theError.toString(); // -> Debugging purposes
        if(theError instanceof UnknownHostException)
            message = context.getResources().getString(R.string.no_conexion_error);

        Toast toast1 =
                Toast.makeText(context,
                        message, Toast.LENGTH_SHORT);

        toast1.show();


        Log.d("Xd",  message );
    }
    public static void showError(Context context, String theError){
        Log.d( "error",theError.toString());
        Toast toast1 =
                Toast.makeText(context,
                        theError.toString(), Toast.LENGTH_SHORT);

        toast1.show();
        Log.d("Xd", "ola k ase");
    }
}
