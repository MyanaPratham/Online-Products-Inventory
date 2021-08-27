package com.onlinemarket.front_end_layer.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.onlinemarket.R
import kotlinx.android.synthetic.main.product_detail_page_activity.*
import kotlinx.android.synthetic.main.toolbar_actvity.*

class ProductDetailPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.product_detail_page_activity)

        initView()
        onClick()
    }
    // Initialization of all view here
    fun initView(){

        // Get the passed values from the list view
        var cost = intent.getStringExtra("cost")
        var name = intent.getStringExtra("name")
        var des = intent.getStringExtra("des")

        // set values to the views
        txtPDTitle.setText(""+name)
        txtPdName.setText(""+name)
        txtCost.setText("Qty:"+cost)
        txtPDDes.setText(""+des)
    }
    // manage all click events
    fun onClick(){
        txtBack.setOnClickListener { finish() }
    }

}