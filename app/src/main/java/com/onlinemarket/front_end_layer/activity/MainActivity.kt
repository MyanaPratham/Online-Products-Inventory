package com.onlinemarket.front_end_layer.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.onlinemarket.R
import com.onlinemarket.integration_layer.model.MyProductData
import com.onlinemarket.integration_layer.model.MyProductModel
import com.onlinemarket.integration_layer.utility.Util.Companion.getJsonDataFromAsset

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // to hide default action bar






        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.navigation_notificationsNew
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        dataCap()
    }

    fun dataCap(){

        val jsonFileString = getJsonDataFromAsset(applicationContext, "demo.json")
        jsonFileString?.let { Log.i("::data:::", it) }

        val gson = Gson()

        var productModel:MyProductModel=gson.fromJson(jsonFileString,MyProductModel::class.java)
        var myProductList:ArrayList<MyProductData> = productModel.product!!
        Log.i(":::data new ::", "Sorted::"+myProductList.size)

        var tags = ArrayList<MyProductData>()
        var addModelProduct=MyProductData()


        // Add the Tag to List

//        val listPersonType = object : TypeToken<List<Person>>() {}.type
//
//        var persons: List<Person> = gson.fromJson(jsonFileString, listPersonType)
//        persons.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }
    }
}