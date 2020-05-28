package co.artist.info.view


import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import co.artist.info.constant.NETWORK_AVAILABLE




open class BaseFragment : Fragment() {
    private val listener: BroadcastReceiver
    internal var TAG = this.javaClass.simpleName

    /**
     * Check Internet availability
     */
    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            val networkState = networkInfo.state
            return networkState == NetworkInfo.State.CONNECTED || networkState == NetworkInfo.State.CONNECTING
        }

    init {
        listener = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                onEventReceived(intent.action)
            }
        }
    }

    fun onEventReceived(eventName: String?) {}

    /**
     * Show Keyboard method
     */
    fun showSoftKeyboard() {
        val inputMethodManager =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    /**
     * hide keyboard method
     */
    fun hideSoftKeyboard() {
        val inputMethodManager = activity!!.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (activity!!.currentFocus != null)
            inputMethodManager.hideSoftInputFromWindow(
                activity!!.currentFocus!!.windowToken, 0
            )
    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)


    }
    override fun getUserVisibleHint(): Boolean {
        return super.getUserVisibleHint()
    }



    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            LocalBroadcastManager.getInstance(context!!).unregisterReceiver(listener)
        } catch (e: Exception) {
            Log.e(TAG, "onDestroyView: $e")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun showToast(message: String?) {
        try {
            Toast.makeText(context, message ?: "", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e(TAG, "showToast: $e")
        }

    }


    override fun onResume() {
        super.onResume()
        try {
            LocalBroadcastManager.getInstance(context!!)
                .registerReceiver(listener, IntentFilter(NETWORK_AVAILABLE))
        } catch (e: Exception) {
            Log.e(TAG, "onResume: $e")
        }

    }

}