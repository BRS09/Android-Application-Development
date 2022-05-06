package com.example.courseproject

import android.content.Intent
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_information.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_secondary.*
import kotlinx.android.synthetic.main.activity_transaction.*
import java.text.DecimalFormat
import java.text.Format

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        //Extras used in the secondary activity
        val extras = intent.extras
        var userIncome = extras?.getDouble("userIncome")
        var username = extras?.getString("username").toString()

        //Set the introMessage and userIncomeText textViews
        introMessage.text = "Hello, " + username + "!"
        userIncomeText.text = DecimalFormat("$##,###").format(userIncome)

        transactionButton.setOnClickListener {
            val transactionIntent = Intent(this, TransactionActivity::class.java)

            startActivity(transactionIntent)
        }

        billsButton.setOnClickListener {
            val billsIntent = Intent(this, BillsActivity::class.java)

            startActivity(billsIntent)
        }

        budgetButton.setOnClickListener {
            val budgetIntent = Intent(this, BudgetActivity::class.java)

            intent.putExtra("radioString", Intent(this,TransactionActivity::class.java).extras?.getString("radioString"))

            budgetIntent.putExtra("transAmount", intent.extras?.getDouble("transAmount"))

            startActivity(budgetIntent)
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