package com.onlinemarket.integration_layer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyProductModel {

    @SerializedName("product")
    @Expose
    var product: ArrayList<MyProductData>? = null
}