package co.artist.info.network

import android.content.Context

import co.artist.info.constant.ApiConstants


interface MainContract {

    interface View {
        fun initView()

        fun setViewData(data: String, view: ApiConstants)
    }

    // for getting data from Api as a pojo class.
    interface Model {
        fun getData(jsonObject: String): String
    }


    interface Presenter {
        fun onClick(caseConstants: ApiConstants, parameters: Array<String>, context: Context, showProgressBar: Boolean?)
    }
}
