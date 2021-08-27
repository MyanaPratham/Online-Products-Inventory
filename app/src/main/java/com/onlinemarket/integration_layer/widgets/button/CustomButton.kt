package com.onlinemarket.integration_layer.widgets.button

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.Button



class CustomButton : Button {


    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    fun init() {

        val tf = Typeface.createFromAsset(context.assets, "font/poppins_medium.ttf")

        typeface = tf

    }
}
