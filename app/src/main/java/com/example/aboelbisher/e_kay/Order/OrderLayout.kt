package com.example.aboelbisher.e_kay.Order

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.aboelbisher.e_kay.R
import android.os.Build
import android.os.Bundle


open class OrderLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = -1) : LinearLayout(context, attr, style) {


    var selectedItemStr: String? = null

    var adapter=OrderMobileAdapter().apply {
        onClickDelegate = { pos, str ->

            setSelectedStrAndEnable(str)

        }
    }

    var dialogShowed = false

     fun setSelectedStrAndEnable(str: String) {
        selectedItemStr = str
        this@OrderLayout.orderBtn.isEnabled = true
    }

    val titleTxtView = TextView(this.context).apply {

        val lftMargin = (resources.displayMetrics.density * 10).toInt()
        val upMargin = (resources.displayMetrics.density * 15).toInt() // (resources.displayMetrics.widthPixels).toInt()

        val width = (resources.displayMetrics.widthPixels).toInt() - 2 * lftMargin
        val height = (resources.displayMetrics.density * 30).toInt()

        val params = android.widget.LinearLayout.LayoutParams(width, height)
        this@OrderLayout.addView(this, params.apply {
            leftMargin = lftMargin
            topMargin = upMargin
        })

        this.gravity = Gravity.LEFT
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)

        setTextColor(Color.BLACK)
    }


    val makeOrderTxtView = TextView(this.context).apply {

        val lftMargin = (resources.displayMetrics.density * 10).toInt() // (resources.displayMetrics.widthPixels).toInt()
        val upMargin = (resources.displayMetrics.density * 8).toInt() // (resources.displayMetrics.widthPixels).toInt()

        val width = (resources.displayMetrics.widthPixels).toInt() - 2 * lftMargin
        val height = (resources.displayMetrics.density * 40).toInt()


        val params = LinearLayout.LayoutParams(width, height)
        this@OrderLayout.addView(this, params.apply {

            leftMargin = lftMargin
            topMargin = upMargin

        })

        this.gravity = Gravity.LEFT
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f)

        setText(R.string.MakeOrder)
    }


    val orderBtn = Button(this.context).apply {

        val width = (resources.displayMetrics.density * 100).toInt()
        val height = (resources.displayMetrics.density * 50).toInt()

        var bMargin = (resources.displayMetrics.density * 15).toInt()
        val params = android.widget.LinearLayout.LayoutParams(width, height)

        this@OrderLayout.addView(this, params.apply {
            this.gravity = Gravity.CENTER
            bottomMargin = bMargin
        })


        this.setOnClickListener({

            this@OrderLayout.selectedItemStr?.let {
                this@OrderLayout.showAlertWithStr(it)
            }

        })

        this.gravity = Gravity.CENTER
        this.setText(R.string.order)


        this.isEnabled = false
    }


    val recycler = RecyclerView(this.context).apply {

        layoutManager = LinearLayoutManager(context)
        adapter =this@OrderLayout.adapter
        this@OrderLayout.post {
            var lftMargin = (resources.displayMetrics.density * 10).toInt() // (resources.displayMetrics.widthPixels).toInt()
            val width = (resources.displayMetrics.widthPixels).toInt() - 2 * lftMargin
            var height = this@OrderLayout.height//(resources.displayMetrics.heightPixels).toInt()

            height = calculateHeight(height)


            val params = android.widget.LinearLayout.LayoutParams(width, height)

            this@OrderLayout.addView(this, 2, params.apply {

                leftMargin = lftMargin
            })

        }

    }

    private fun calculateHeight(height: Int): Int
    {
        var height1 = height
        height1 -= (this@OrderLayout.titleTxtView.height +
                (this@OrderLayout.titleTxtView.layoutParams as LayoutParams).topMargin +
                this@OrderLayout.makeOrderTxtView.height +
                (this@OrderLayout.makeOrderTxtView.layoutParams as LayoutParams).topMargin +
                this@OrderLayout.orderBtn.height +
                (this@OrderLayout.orderBtn.layoutParams as LayoutParams).bottomMargin)
        return height1
    }


    init
    {
        this.orientation = VERTICAL
        this.setBackgroundColor(Color.WHITE)
    }


    private fun showAlertWithStr(str: String)
    {
        this.dialogShowed = true
        AlertDialog.Builder(this.context)
                .setTitle("E-Kay")
                .setMessage("Approve order for: " + str )
                .setPositiveButton("YES", { dialog, which ->

                    this.dialogShowed = false

                    Toast.makeText(context, "Order sent!", Toast.LENGTH_SHORT).show()
                }).setNegativeButton("NO", { dialog, which ->

                    this.dialogShowed = false

                }).show()

    }


    fun getSavedInstance(savedInstance : Bundle?)
    {

        savedInstance?.let {
            args->

            try
            {

                this.adapter.selectedIndex=args.getInt("SELECTED_INDEX",-1)
                this.adapter.notifyDataSetChanged()
                if (this.adapter.selectedIndex != -1)
                {
                    val selectedStr = this.adapter.data[this.adapter.selectedIndex]
                    this.setSelectedStrAndEnable(selectedStr)

                    this.dialogShowed = args.getBoolean("DIALOG_SHOWED" , false)
                    if (this.dialogShowed)
                    {
                        this.showAlertWithStr(selectedStr)
                    }
                }
            }

            catch (e : Exception)
            {

            }

        }
    }


    fun setSavedInstance(savedInstance : Bundle?)
    {
        savedInstance?.let {
            args->
            args.putInt("SELECTED_INDEX",this.adapter.selectedIndex)
            args.putBoolean("DIALOG_SHOWED" , this.dialogShowed)
        }
    }


}