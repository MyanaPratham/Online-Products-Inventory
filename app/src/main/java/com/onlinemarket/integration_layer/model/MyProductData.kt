package com.onlinemarket.integration_layer.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MyProductData {

    @SerializedName("sku")
    @Expose
    var sku: String? = null

    @SerializedName("product")
    @Expose
    var product: String? = null

    @SerializedName("company")
    @Expose
    var company: String? = null

    @SerializedName("qty")
    @Expose
    var qty: Int? = null
}