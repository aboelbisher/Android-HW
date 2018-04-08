package com.example.aboelbisher.e_kay

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.inputmethod.EditorInfo
import android.widget.*

/*
 * Created by aboelbisher on 3/25/18.
 */
class PortraitLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = -1) : BaseLayout(context, attr, style)
{


    val fullNameTxtKey = "fullNameKey"
    val ageTxtKey = "ageTxtKey"
    val spinnerKey = "spinnerKey"



    val txtEditWatcher: TextWatcher = object : TextWatcher
    {

        override fun afterTextChanged(p0: Editable?)
        {
            this@PortraitLayout.checkbtnEnablness()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    }



    val lftMargin = (this.resources.displayMetrics.density * 10).toInt()

    val headerTxtView = TextView(context).apply {
        setText(R.string.header)
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30f)

        setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryText))
    }


    val fullNameTxtView = TextView(context)
    var fullNameEditTxt = EditText(context)


    var ageTxtView = TextView(context)
    val ageEditTxt = EditText(context)


    val countryTxtView = TextView(context)


    val spinner = Spinner(context)


    val signUpBtn = Button(context).apply {

        setTextColor(Color.WHITE)
        setText(R.string.SignUp)
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
    }


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
        this.ageEditTxt.inputType = InputType.TYPE_CLASS_NUMBER
        this.initTxtView(this.countryTxtView , R.string.country)
        this.initSpinner()
        this.initSignInBtn()
    }


    private fun initTxtView(txtView : TextView , txt : Int)
    {

        txtView.setText(txt)
        txtView.setTextSize(TypedValue.COMPLEX_UNIT_DIP , 20f)
        txtView.setTextColor(ContextCompat.getColor(context, R.color.colorSecondrayText))




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

        editTxt.addTextChangedListener(this.txtEditWatcher)
        editTxt.setSingleLine(true)

        editTxt.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))

        editTxt.imeOptions = EditorInfo.IME_ACTION_DONE

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
        val adapter = ArrayAdapter.createFromResource(this.context,
                R.array.countries_arr, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.spinner.adapter = adapter

    }



    private fun initSignInBtn()
    {
        this.addView(this.signUpBtn , LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            topMargin =  (this@PortraitLayout.resources.displayMetrics.density * 50).toInt()
            bottomMargin = (this@PortraitLayout.resources.displayMetrics.density * 10).toInt()
            gravity = android.view.Gravity.CENTER_HORIZONTAL
        })

        this.signUpBtn.setOnClickListener({

                this@PortraitLayout.signUpBtnClicked()

            })


        this.signUpBtn.isEnabled = false
    }

    private fun signUpBtnClicked()
    {
        Toast.makeText(this.context , R.string.userCreated , Toast.LENGTH_SHORT).show()
    }


    private fun checkbtnEnablness()
    {
        if (this@PortraitLayout.validateInputInfo())
        {
            this@PortraitLayout.signUpBtn.isEnabled = true
            this@PortraitLayout.signUpBtn.alpha = 1f
        }
        else
        {
            this@PortraitLayout.signUpBtn.isEnabled = false
            this@PortraitLayout.signUpBtn.alpha = 0.5f
        }
    }

    private fun  validateInputInfo() : Boolean
    {
        try
        {

            if (InfoValidator.Companion.checkIfLegalAge(this.ageEditTxt.text.toString()))
            {
                return InfoValidator.Companion.checkIfLegalName(this.fullNameEditTxt.text.toString())
            }
            else
            {
                return false
            }
        }
        catch (e : Exception)
        {
            return false
        }
    }

    fun getSavedInstance(savedInstance : Bundle?)
    {


        savedInstance?.let {

            try
            {
                this.fullNameEditTxt.setText(it.getString(this.fullNameTxtKey))
                this.ageEditTxt.setText(it.getString(this.ageTxtKey))

                this@PortraitLayout.checkbtnEnablness()
                this@PortraitLayout.signUpBtn.isEnabled = this@PortraitLayout.validateInputInfo()

                this.spinner.setSelection(it.getInt(this@PortraitLayout.spinnerKey))

            }
            catch (e : Exception)
            {

            }

        }
    }


    fun setSavedInstance(savedInstance : Bundle?)
    {
        savedInstance?.let {
            it.putString(this.fullNameTxtKey , this.fullNameEditTxt.text.toString())
            it.putString(this.ageTxtKey , this.ageEditTxt.text.toString())
            it.putInt (this.spinnerKey , this.spinner.selectedItemPosition)
        }
    }

}


























