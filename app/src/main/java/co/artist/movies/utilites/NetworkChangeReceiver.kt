package co.artist.info.utilites

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import co.artist.info.constant.NETWORK_AVAILABLE


class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val networkInfo = intent.getParcelableExtra<NetworkInfo>(ConnectivityManager.EXTRA_NETWORK_INFO)
            if (networkInfo != null && networkInfo.detailedState == NetworkInfo.DetailedState.CONNECTED) {
                Log.d("Network", "Internet YAY")
                //                NetworkDialog.hideDialog();
                LocalBroadcastManager.getInstance(context).sendBroadcast(Intent(NETWORK_AVAILABLE))
            } else if (networkInfo != null && networkInfo.detailedState == NetworkInfo.DetailedState.DISCONNECTED) {
                Log.d("Network", "No internet :(")
                //                NetworkDialog.showDialog(AppDetails.getContext());
            }
        }
    }
}
