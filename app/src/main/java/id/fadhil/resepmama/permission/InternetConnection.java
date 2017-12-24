package id.fadhil.resepmama.permission;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by riyan on 20/12/2017.
 */

public class InternetConnection {
    public static boolean checkConnection(Context context){

        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
