package com.example.aboelbisher.e_kay

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView

class MainActivity : AppCompatActivity()
{



    lateinit var scrollView : ScrollView
    var portraitLayout : PortraitLayout? = null

    var landscapeLayout : LandscapeLayout? = null


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)


        val orientation = resources.configuration.orientation

        if(orientation == ORIENTATION_LANDSCAPE)
        {
            Log.d("tag" , "LANDSCAPE")

        }
        else
        {
            Log.d("tag" , "PORTRAIT")
        }

//        this.scrollView = ScrollView(this)
//        this.portraitLayout =  ParentLayout(this , orientation)
//        this.setContentView(this.scrollView)
//        this.scrollView.addView(this.portraitLayout)
//
//        this.portraitLayout.getSavedInstance(savedInstanceState)
    }


    override fun onSaveInstanceState(outState: Bundle?)
    {
        super.onSaveInstanceState(outState)


        this.portraitLayout!!.setSavedInstance(outState)

    }
}
