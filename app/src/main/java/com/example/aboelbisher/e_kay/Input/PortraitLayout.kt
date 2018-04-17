package com.example.aboelbisher.e_kay.Input

import android.content.Context
import android.util.AttributeSet
import android.widget.*
import com.example.aboelbisher.e_kay.BaseLayout
import com.example.aboelbisher.e_kay.R

/*
 * Created by aboelbisher on 3/25/18.
 */
class PortraitLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = -1) : BaseLayout(context, attr, style)
{


    val lftMargin = (this.resources.displayMetrics.density * 10).toInt()


    init
    {
        this.orientation = LinearLayout.VERTICAL
        this.initSubViews()
    }


    private fun initSubViews()
    {
        this.addView(headerTxtView, LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            setMargins(this@PortraitLayout.lftMargin, (this@PortraitLayout.resources.displayMetrics.density * 10).toInt(), 0, 0)

        })

        this.initTxtView(this.fullNameTxtView , R.string.fullName)
        this.initEditTxt(this.fullNameEditTxt)
        this.initTxtView(this.ageTxtView , R.string.age)
        this.initEditTxt(this.ageEditTxt)
        this.initTxtView(this.countryTxtView , R.string.country)
        this.initSpinner()
        this.initSignInBtn()
    }


    private fun initTxtView(txtView : TextView , txt : Int)
    {

        txtView.setText(txt)


        this.addView(txtView , LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT).apply {

            val upMargin =  (this@PortraitLayout.resources.displayMetrics.density * 25).toInt()
            setMargins(this@PortraitLayout.lftMargin, upMargin, 0, 0)
        })
    }


    private fun initEditTxt(editTxt : EditText)
    {
        val editTxtHeight  = (this@PortraitLayout.resources.displayMetrics.density * 45).toInt()
        val editTxtWidth = (this@PortraitLayout.resources.displayMetrics.widthPixels).toInt() - 2 * lftMargin


        this.addView(editTxt , LinearLayout.LayoutParams(editTxtWidth , editTxtHeight).apply {
            setMargins(this@PortraitLayout.lftMargin, 0 , 0, 0)
        })
    }


    private fun initSpinner()
    {
        this.addView(this.spinner , LinearLayout.LayoutParams((this@PortraitLayout.resources.displayMetrics.density * 250).toInt() ,
                (this@PortraitLayout.resources.displayMetrics.density * 25).toInt() ).apply {
            setMargins(this@PortraitLayout.lftMargin , (this@PortraitLayout.resources.displayMetrics.density * 4).toInt() , 0 , 0)

        })


    }



    private fun initSignInBtn()
    {
        this.addView(this.signUpBtn , LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            topMargin =  (this@PortraitLayout.resources.displayMetrics.density * 50).toInt()
            bottomMargin = (this@PortraitLayout.resources.displayMetrics.density * 10).toInt()
            gravity = android.view.Gravity.CENTER_HORIZONTAL
        })


    }








}


























