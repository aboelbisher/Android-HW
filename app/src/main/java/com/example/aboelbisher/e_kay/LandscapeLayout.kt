package com.example.aboelbisher.e_kay

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class LandscapeLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = -1) : BaseLayout(context, attr, style)
{

    lateinit var lftLayout : LinearLayout
    lateinit var rghtLayout : LinearLayout


    val lftMargin = (this.resources.displayMetrics.density * 10).toInt()


    init
    {
        this.orientation = HORIZONTAL
        this.initSubViews()
    }




    private fun initSubViews()
    {
        this.initLftLayout()
        this.initRghtLayout()
    }



    private fun initLftLayout()
    {
        this.lftLayout = LinearLayout(this.context)
        this.lftLayout.orientation = VERTICAL
        val lftHeight  = (this.resources.displayMetrics.heightPixels).toInt()
        val lftWidth = (this.resources.displayMetrics.widthPixels).toInt() / 2
        this.addView(this.lftLayout , LinearLayout.LayoutParams(lftWidth, lftHeight))
//        this.lftLayout.setBackgroundColor(R.color.colorPrimaryDark)


        this.lftLayout.addView(headerTxtView, LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            setMargins((resources.displayMetrics.density * 10).toInt(), (resources.displayMetrics.density * 10).toInt(), 0, 0)

        })


        this.initTxtView(this.fullNameTxtView , R.string.fullName , this.lftLayout)
        this.initEditTxt(this.fullNameEditTxt , this.lftLayout)
        this.initTxtView(this.ageTxtView , R.string.age , this.lftLayout)

        this.initEditTxt(this.ageEditTxt , this.lftLayout)

    }





    private fun initRghtLayout()
    {
        this.rghtLayout = LinearLayout(this.context)
        this.rghtLayout.orientation = VERTICAL

        val rghtHeight  = (this.resources.displayMetrics.heightPixels).toInt()
        val rghtWdith = (this.resources.displayMetrics.widthPixels).toInt() / 2

        this.addView(this.rghtLayout , LinearLayout.LayoutParams(rghtWdith, rghtHeight))


        this.initTxtView(this.countryTxtView , R.string.country , this.rghtLayout)


        this.rghtLayout.addView(this.spinner , LinearLayout.LayoutParams((this.rghtLayout.resources.displayMetrics.widthPixels).toInt() ,
                (resources.displayMetrics.density * 25).toInt() ).apply {
            setMargins(lftMargin, (resources.displayMetrics.density * 8).toInt() , 0 , 0)

        })


        this.rghtLayout.addView(this.signUpBtn , LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT).apply {

            topMargin =  (resources.displayMetrics.density * 50).toInt()
            bottomMargin = (resources.displayMetrics.density * 10).toInt()
            gravity = android.view.Gravity.CENTER_HORIZONTAL
        })



    }






    private fun initTxtView(txtView : TextView, txt : Int, layout : LinearLayout)
    {
        txtView.setText(txt)

        layout.addView(txtView , LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT).apply {

            val upMargin =  (resources.displayMetrics.density * 25).toInt()
            setMargins(lftMargin, upMargin, 0, 0)
        })


    }


    private fun initEditTxt(editTxt : EditText , layout : LinearLayout)
    {
        val editTxtHeight  = (resources.displayMetrics.density * 45).toInt()
        val editTxtWidth = ((this.resources.displayMetrics.widthPixels).toInt() / 2 ) - 2 * lftMargin


        layout.addView(editTxt , LinearLayout.LayoutParams(editTxtWidth , editTxtHeight).apply {
            setMargins(lftMargin, 0 , 0, 0)
        })

    }
}