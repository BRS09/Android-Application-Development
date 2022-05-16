package com.example.courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        //Register button on click listener
        registerButton.setOnClickListener {
            //Collect the entered username and password and store them in these variables
            if(registerUsername.text.trim().toString().isNotEmpty() || registerPassword.text.trim().toString().isNotEmpty()) {
                createUser(registerUsername.text.trim().toString(), registerPassword.text.trim().toString())
            }
            else{
                Snackbar.make(findViewById(R.id.registerButton), "Check your username and password then try again", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun createUser(email: String, password: String){
        var user = hashMapOf(
            "Username" to registerUsername.text.trim().toString(),
            "Income" to annualIncome.text.toString().toDouble()
        )
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    db.collection("UserInfo").document("Users").set(user).addOnCompleteListener {
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
                else{
                    Snackbar.make(findViewById(R.id.registerButton), "Enter a valid username and password (6 characters)", Snackbar.LENGTH_LONG).show()
                }
            }
    }
}