package com.example.aboelbisher.e_kay

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class OrderActivity : AppCompatActivity()
{
    lateinit var orderLayout : OrderLayout


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        this.orderLayout = OrderLayout(this)
        this.setContentView(this.orderLayout)


        val str = resources.getString(R.string.welcome) + "\n" + " " + this.intent.getStringExtra(MY_EXTRA_MESSAGE) + "!"
        this.orderLayout.titleTxtView.setText(str)
    }











}