package  com.onlinemarket.integration_layer.widgets.edittext

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class CustomEditText : androidx.appcompat.widget.AppCompatEditText {

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
        val font = Typeface.createFromAsset(context.getAssets(), "font/poppinsRegular.ttf")

        setTypeface(font, Typeface.NORMAL)

    }

}