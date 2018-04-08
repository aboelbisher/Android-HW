package com.example.aboelbisher.e_kay

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.inputmethod.EditorInfo
import android.widget.*


const val MY_EXTRA_MESSAGE = "EXTRA_MESSAGE"


interface BaseLayoutDelegate
{
    fun signUpBtnClicked(str : String)
}


open class BaseLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = -1) : LinearLayout(context, attr, style)
{
    var delegate : BaseLayoutDelegate? = null

    val fullNameTxtKey = "fullNameKey"
    val ageTxtKey = "ageTxtKey"
    val spinnerKey = "spinnerKey"


    val signUpBtn = Button(context).apply {

        setTextColor(Color.WHITE)
        setText(R.string.SignUp)
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))

        this.setOnClickListener({

            signUpBtnClicked()
        })

        this.isEnabled = false
    }

    val txtEditWatcher: TextWatcher = object : TextWatcher
    {

        override fun afterTextChanged(p0: Editable?)
        {
            this@BaseLayout.checkbtnEnablness()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    }




    val headerTxtView = TextView(context).apply {

        setText(R.string.header)
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30f)
        setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryText))

    }


    val fullNameTxtView = TextView(context).apply {
        this@BaseLayout.cusomizeTxtView(this)
    }

    var fullNameEditTxt = EditText(context).apply {
        this@BaseLayout.configureEditTxt(this)
    }


    var ageTxtView = TextView(context).apply {
        this@BaseLayout.cusomizeTxtView(this)


    }


    val ageEditTxt = EditText(context).apply {
        this@BaseLayout.configureEditTxt(this)

        this.inputType = InputType.TYPE_CLASS_NUMBER

    }

    val countryTxtView = TextView(context).apply {
        this@BaseLayout.cusomizeTxtView(this)
    }


    val spinner = Spinner(context).apply {

        val adapter = ArrayAdapter.createFromResource(this.context,
                R.array.countries_arr, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.adapter = adapter
    }








    private fun configureEditTxt(editTxt : EditText)
    {
        editTxt.addTextChangedListener(this.txtEditWatcher)
        editTxt.setSingleLine(true)

        editTxt.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))

        editTxt.imeOptions = EditorInfo.IME_ACTION_DONE

    }


    private fun cusomizeTxtView(txtView : TextView)
    {
        txtView.setTextSize(TypedValue.COMPLEX_UNIT_DIP , 20f)
        txtView.setTextColor(ContextCompat.getColor(context, R.color.colorSecondrayText))

    }



    private fun checkbtnEnablness()
    {
        if (this@BaseLayout.validateInputInfo())
        {
            this@BaseLayout.signUpBtn.isEnabled = true
            this@BaseLayout.signUpBtn.alpha = 1f
        }
        else
        {
            this@BaseLayout.signUpBtn.isEnabled = false
            this@BaseLayout.signUpBtn.alpha = 0.5f
        }
    }


    fun getSavedInstance(savedInstance : Bundle?)
    {


        savedInstance?.let {

            try
            {
                this.fullNameEditTxt.setText(it.getString(this.fullNameTxtKey))
                this.ageEditTxt.setText(it.getString(this.ageTxtKey))

                this@BaseLayout.checkbtnEnablness()
                this@BaseLayout.signUpBtn.isEnabled = this@BaseLayout.validateInputInfo()

                this.spinner.setSelection(it.getInt(this@BaseLayout.spinnerKey))

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



    private fun signUpBtnClicked()
    {

        this.delegate?.signUpBtnClicked(this.fullNameEditTxt.text.toString())

    }




}
