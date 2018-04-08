package com.example.aboelbisher.e_kay

import android.content.Intent
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.ScrollView

class MainActivity : AppCompatActivity() , BaseLayoutDelegate
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
            this.landscapeLayout = LandscapeLayout(this)
            this.setContentView(this.landscapeLayout)
            this.landscapeLayout!!.getSavedInstance(savedInstanceState)
            this.landscapeLayout!!.delegate = this
        }
        else
        {
            this.portraitLayout = PortraitLayout(this)
            this.setContentView(this.portraitLayout)
            this.portraitLayout!!.getSavedInstance(savedInstanceState)
            this.portraitLayout!!.delegate = this
        }
    }


    override fun onSaveInstanceState(outState: Bundle?)
    {
        super.onSaveInstanceState(outState)

        if (this.portraitLayout != null)
        {
            this.portraitLayout!!.setSavedInstance(outState)
        }
        else if (this.landscapeLayout != null)
        {
            this.landscapeLayout!!.setSavedInstance(outState)
        }


    }


    override fun signUpBtnClicked(str : String)
    {
        val intent = Intent(this , OrderActivity::class.java).apply {

            putExtra(MY_EXTRA_MESSAGE, str)
        }

        startActivity(intent)
    }
}
