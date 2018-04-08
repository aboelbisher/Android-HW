package com.example.aboelbisher.e_kay

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

open class OrderLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = -1) : LinearLayout(context, attr, style)
{
    val titleTxtView = TextView(this.context).apply {

        val lftMargin = (resources.displayMetrics.density * 10).toInt() // (resources.displayMetrics.widthPixels).toInt()
        val width = (resources.displayMetrics.widthPixels).toInt() - 2 * lftMargin
        val height = (resources.displayMetrics.density * 400).toInt()


        val params =  android.widget.LinearLayout.LayoutParams(width , height )
        this@OrderLayout.addView(this , params.apply {
//            topMargin = (resources.displayMetrics.density * 200).toInt()
//            leftMargin = (resources.displayMetrics.density * 10).toInt()
        })

        this.gravity = Gravity.CENTER
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35f)





        setTextColor(Color.BLACK)
    }



    init
    {
        this.orientation = VERTICAL
        this.setBackgroundColor(Color.WHITE)
    }


}