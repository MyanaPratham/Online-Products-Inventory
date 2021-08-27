package com.onlinemarket.integration_layer.widgets.textview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class CustomLightTextView  : androidx.appcompat.widget.AppCompatTextView {

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
        val tf = Typeface.createFromAsset(context.assets, "font/poppins_regular.ttf")
        setTypeface(tf, Typeface.NORMAL)

    }

}
