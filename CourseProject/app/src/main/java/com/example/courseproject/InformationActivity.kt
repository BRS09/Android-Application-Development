package com.example.courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_information.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class InformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        //Extras and intent used
        val extras = intent.extras
        val secondaryIntent = Intent(this, SecondaryActivity::class.java)

        //Submit button on click listener
        infoButton.setOnClickListener {
            //Store the text in the userIncome input field
            val userIncome = userIncome.text.toString().toDouble()
            //Put the userIncome and username variables in secondaryIntent
            secondaryIntent.putExtra("userIncome", userIncome)
            secondaryIntent.putExtra("username", extras?.getString("username"))
            //Send the user to the secondaryIntent
            startActivity(secondaryIntent)
        }
    }
}