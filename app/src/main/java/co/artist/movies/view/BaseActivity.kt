package co.artist.info.view


import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.artist.info.utilites.AppDetails
import co.artist.info.utilites.NetworkChangeReceiver


open class BaseActivity : AppCompatActivity() {
    var TAG = BaseActivity::class.java.simpleName
    var networkChangeReceiver: NetworkChangeReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            AppDetails.activity = this
            AppDetails.context = this
            networkChangeReceiver = NetworkChangeReceiver()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver)
        }
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        try {
            networkChangeReceiver = NetworkChangeReceiver()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

    override fun onResume() {
        try {
            AppDetails.activity = this
            AppDetails.context = this
            registerReceiver(
                networkChangeReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onResume()
    }


}
