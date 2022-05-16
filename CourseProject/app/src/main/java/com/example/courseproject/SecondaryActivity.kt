package com.example.courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_secondary.*
import java.text.DecimalFormat

class SecondaryActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        auth = FirebaseAuth.getInstance()

        db.collection("UserInfo")
            .whereEqualTo("Username", auth.currentUser?.email)
            .get()
            .addOnSuccessListener{ documents ->
                for (document in documents){
                    userIncomeText.text = DecimalFormat("$##,###.##").format(document.get("Income").toString().toDouble())
                }
            }

        logoutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }

        transactionButton.setOnClickListener {
            val transactionIntent = Intent(this, TransactionActivity::class.java)

            startActivity(transactionIntent)
        }

        billsButton.setOnClickListener {
            val billsIntent = Intent(this, BillsActivity::class.java)

            startActivity(billsIntent)
        }

        helpButton.setOnClickListener {
            val helpIntent = Intent(this, HelpActivity::class.java)

            startActivity(helpIntent)
        }

        preferencesButton.setOnClickListener {
            val preferencesIntent = Intent(this, PreferencesActivity::class.java)

            startActivity(preferencesIntent)
        }


    }
}