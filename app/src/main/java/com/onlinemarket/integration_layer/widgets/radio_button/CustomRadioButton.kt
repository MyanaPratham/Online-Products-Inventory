package com.onlinemarket.integration_layer.widgets.radio_button

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.RadioButton


class CustomRadioButton : RadioButton {

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
        typeface = tf

    }
}
