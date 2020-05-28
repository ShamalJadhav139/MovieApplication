package co.artist.info.presenter


import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import co.artist.info.constant.ApiConstants
import co.artist.info.network.ApiClient
import co.artist.info.network.ApiInterface
import co.artist.info.network.MainContract
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityPresenter(private val mView: MainContract.View) : MainContract.Presenter {
    private var progressDialog: ProgressDialog? = null

    companion object {
        val TAG = MainActivityPresenter.javaClass.name
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE
            ).state == NetworkInfo.State.CONNECTING ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI
            ).state == NetworkInfo.State.CONNECTING
        }

    }

    init {
        initPresenter()
    }

    private fun initPresenter() {
        mView.initView()
    }

    override fun onClick(
        caseConstants: ApiConstants,
        para: Array<String>,
        context: Context,
        showProgressBar: Boolean?
    ) {
        if (showProgressBar!!) {
            initAndShowProgressBar(context)
        }
        val retrofitWeather = ApiClient.movieClient
        val weatherRequestInterface = retrofitWeather!!.create(ApiInterface::class.java)
        val accessTokenCall: retrofit2.Call<JsonObject>
        when (caseConstants) {
            ApiConstants.getGenreList -> {
                accessTokenCall = weatherRequestInterface.getGenreList(para[0])
                callApiWithAccessToken(accessTokenCall, context, ApiConstants.getGenreList)
            }
        }
    }

    private fun callApiWithAccessToken(
        accessTokenCall: Call<JsonObject>,
        context: Context,
        view: ApiConstants
    ) {
        try {
            if (isNetworkAvailable(context)) {
                accessTokenCall.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        if (progressDialog != null && progressDialog!!.isShowing)
                            try {
                                if (progressDialog!!.isShowing)
                                    progressDialog!!.dismiss()
                            } catch (e: Exception) {
                                Log.e(TAG, "onResponse: $e")
                            }

                        if (response.code() == 200) {
                            mView.setViewData(response.body().toString(), view)
                        }else{
                            Toast.makeText(context, "Server under maintenance", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        try {
                            if (progressDialog != null && progressDialog!!.isShowing)
                                progressDialog!!.dismiss()
                        } catch (e: Exception) {
                            Log.e(TAG, "onFailure: $e")
                        }
                        t.printStackTrace()
                    }
                })
            } else {
                if (progressDialog!!.isShowing)
                    try {
                        progressDialog!!.dismiss()
                    } catch (e: Exception) {
                        Log.e(TAG, "onResponse: $e")
                    }
            }
        } catch (e: Exception) {
            try {
                if (progressDialog!!.isShowing)
                    progressDialog!!.dismiss()
            } catch (e1: Exception) {
                Log.e(TAG, "callApiWithAccessToken: $e1")
            }
            e.printStackTrace()
        }

    }


    private fun initAndShowProgressBar(context: Context) {
        progressDialog = ProgressDialog.show(context, "Please wait..", "")
        progressDialog!!.isIndeterminate = true
        progressDialog!!.setCancelable(false)
        try {
            progressDialog!!.show()
        } catch (e: Exception) {
            Log.e(TAG, "initAndShowProgressBar: $e")
        }
    }


}
