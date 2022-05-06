package com.example.courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.BundleCompat
import androidx.lifecycle.LiveData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables, intents, and extras used
        val username = usernameInput.text
        val password = passwordInput.text
        val registerIntent = Intent(this, RegisterActivity::class.java)
        val secondaryIntent = Intent(this, SecondaryActivity::class.java)
        val infoIntent = Intent(this, InformationActivity::class.java)
        val extras = intent.extras


        //Login button on click listener
        loginButton.setOnClickListener {
            //If extras is not null, get registered username and password, and check if they are equal to the entered username and password
            if(extras != null){
                val registeredUsername = extras.getString("registeredUsername")
                val registeredPassword = extras.getString("registeredPassword")

                if(username.toString() == registeredUsername && password.toString() == registeredPassword){
                    infoIntent.putExtra("username", username.toString())
                    startActivity(infoIntent)
                }
            }
            //If not, take the user to the register page
            else {
                startActivity(registerIntent)
            }
        }

        //Sign up button on click listener
        signupButton.setOnClickListener {
            //Send the user to the register page
            startActivity(registerIntent)
        }
    }
}