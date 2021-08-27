package com.onlinemarket.integration_layer.utility

import android.R
import android.app.Application
import android.content.Context
import androidx.core.content.res.ResourcesCompat


class MyApplication : Application() {

    var context: Context? = null;
    private var mInstance: MyApplication? = null

    override fun onCreate() {
        super.onCreate()

        mInstance = this
        mInstance = MyApplication()
        this.context = applicationContext

        val sharedPreferenceManager = SharedPreferenceManager(applicationContext)
        SharedPreferenceManager.initializeInstance(applicationContext)
    }

    companion object {
        val BASE_URL = "https://www.freeforexapi.com/api/"
    }

}