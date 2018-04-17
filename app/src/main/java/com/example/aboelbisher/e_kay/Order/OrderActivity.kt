package com.example.aboelbisher.e_kay.Order

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.aboelbisher.e_kay.MY_EXTRA_MESSAGE
import com.example.aboelbisher.e_kay.R

class OrderActivity : AppCompatActivity()
{
    lateinit var orderLayout : OrderLayout

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        Log.d("OrderActivity1" , "onCreate")


        this.orderLayout = OrderLayout(this)
        this.setContentView(this.orderLayout)

        this.orderLayout.getSavedInstance(savedInstanceState)

        val str = resources.getString(R.string.welcome) + " " + this.intent.getStringExtra(MY_EXTRA_MESSAGE) + ""
        this.orderLayout.titleTxtView.setText(str)


        this.setTitle(R.string.ordernfo)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        this.orderLayout.setSavedInstance(outState)
    }
    override fun onBackPressed()
    {
        finish()


    }


    override fun onPause() {
        super.onPause()

        Log.d("OrderActivity1" , "onPause")
    }

    override fun onStart()
    {
        super.onStart()
        Log.d("OrderActivity1" , "OnStart")

    }


    override fun onResume() {
        super.onResume()

        Log.d("OrderActivity1" , "onResume")

    }

    override fun onStop() {
        super.onStop()

        Log.d("OrderActivity1" , "OnStop")

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("OrderActivity1" , "onDestroy")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("OrderActivity1" , "onRestart")

    }



}









