package com.example.courseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_preferences.*
import kotlinx.android.synthetic.main.activity_preferences.view.*
import kotlinx.android.synthetic.main.activity_secondary.*
import java.text.DecimalFormat

class PreferencesActivity : AppCompatActivity() {
    val db = Firebase.firestore
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val actionbar = supportActionBar
        auth = FirebaseAuth.getInstance()

        actionbar?.setDisplayHomeAsUpEnabled(true)

        resetPasswordButton.setOnClickListener {
            auth.sendPasswordResetEmail(auth.currentUser?.email.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Password reset email sent", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this, "Error, please try again", Toast.LENGTH_LONG).show()
                }
            }
        }

        db.collection("SwitchToggle").document("State").get().addOnCompleteListener { task ->
            if(task.isSuccessful){
                darkModeButton.setOnClickListener {
                    if(task.result.get("State") == false){
                        db.collection("SwitchToggle").document("State").update("State", true)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    else{
                        db.collection("SwitchToggle").document("State").update("State", false)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }
    }
}