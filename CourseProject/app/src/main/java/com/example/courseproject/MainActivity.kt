package com.example.courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.BundleCompat
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        var darkState = hashMapOf(
            "State" to false
        )

        db.collection("SwitchToggle").document("State").get().addOnCompleteListener {
            if(it.isSuccessful){
                when(it.result.get("State")){
                    true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    else -> db.collection("SwitchToggle").document("State").set(darkState)
                }
            }
        }

        //Variables, intents, and extras used
        val registerIntent = Intent(this, RegisterActivity::class.java)

        loginButton.setOnClickListener {
            if(usernameInput.text.trim().toString().isNotEmpty() || passwordInput.text.trim().toString().isNotEmpty()){
                login(usernameInput.text.trim().toString(), passwordInput.text.trim().toString())
            }
            else{
                Snackbar.make(findViewById(R.id.loginButton), "Check your username or password then try again", Snackbar.LENGTH_LONG).show()
            }
        }

        //Sign up button on click listener
        signupButton.setOnClickListener {
            //Send the user to the register page
            startActivity(registerIntent)
        }
    }

    fun login(email: String, password: String){
        var collection = db.collection("UserInfo").document("Users").get()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    startActivity(Intent(this, SecondaryActivity::class.java))
                }
                else{
                    Snackbar.make(findViewById(R.id.loginButton), "Enter a valid username or password", Snackbar.LENGTH_LONG).show()
                }
            }
    }
}