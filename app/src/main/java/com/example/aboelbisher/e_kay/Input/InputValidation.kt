package com.example.aboelbisher.e_kay.Input

class InfoValidator
{
    companion object
    {


        val MIN_AGE = 16
        val MAX_AGE = 100

        fun checkIfLegalAge(ageStr: String): Boolean
        {
            val age = ageStr.replace("\\s".toRegex(), "").toInt()

            return (age >= MIN_AGE && age <= MAX_AGE)
        }


        fun checkIfLegalName(name : String) : Boolean
        {
            val txt = name.replace("\\s".toRegex() , "")
            return txt.matches("[a-zA-Z]+".toRegex())
        }

    }

}