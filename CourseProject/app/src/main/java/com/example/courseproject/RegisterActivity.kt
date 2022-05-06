package com.example.courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Register button on click listener
        registerButton.setOnClickListener {
            //Collect the entered username and password and store them in these variables
            var registeredUsername = registerUsername?.text
            var registeredPassword = registerPassword?.text

            //Create the login intent
            val loginIntent = Intent(this, MainActivity::class.java)
            //Put the entered username and password in the loginIntent
            loginIntent.putExtra("registeredUsername", registeredUsername.toString())
            loginIntent.putExtra("registeredPassword", registeredPassword.toString())
            //Send the user to the login page
            startActivity(loginIntent)
        }
    }
}