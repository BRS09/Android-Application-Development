package com.example.courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.add_transaction.*
import java.text.DecimalFormat

class TransactionActivity : AppCompatActivity() {
    companion object{
        var amountAndCategory: HashMap<String, Double> = HashMap()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        val mainLayout: ConstraintLayout = findViewById(R.id.transactionActivity)
        val actionbar = supportActionBar
        var transTotal = 0.0
        val decimalFormat = DecimalFormat("$##,###.##")
        var radioString: String = ""
        var transAmount: Double = 0.0

        val transPopUp: View = LayoutInflater.from(this).inflate(R.layout.add_transaction, mainLayout, false)
        transPopUp.setBackgroundColor(ContextCompat.getColor(this, androidx.appcompat.R.color.tooltip_background_light))

        actionbar?.setDisplayHomeAsUpEnabled(true)

        floatingActionButton.setOnClickListener {
            mainLayout.addView(transPopUp)
            transactionActivity.removeView(floatingActionButton)

            if(addTransactionButton.isVisible){
                addTransactionButton?.setOnClickListener {
                    val row: TableRow = TableRow(this)
                    val tableRowParams: TableRow.LayoutParams = TableRow.LayoutParams (TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f)
                    transTotal = transTotal.plus(transactionAmount.text.toString().toDouble())
                    transactionTotal.text = decimalFormat.format(transTotal)

                    when(transCategoryRadioGroup.checkedRadioButtonId){
                        foodRadioButton.id -> radioString = "Food"
                        utilitiesRadioButton.id -> radioString = "Utilities"
                        transportRadioButton.id -> radioString = "Transportation"
                        entertainRadioButton.id -> radioString = "Entertainment"
                        shoppingRadioButton.id -> radioString = "Shopping"
                        personalRadioButton.id -> radioString = "Personal"
                    }

                    val arr = arrayOf(transactionName.text, transactionAmount.text, radioString, transactionDate.text)

                    for(n in arr){
                        val textView: TextView = TextView(this)
                        if(n == transactionAmount.text) {
                            textView.text = decimalFormat.format(n.toString().toDouble())
                            transAmount = transactionAmount.text.toString().toDouble()
                        }
                        else textView.text = n
                        row.addView(textView, tableRowParams)
                        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textView.setPadding(0, 8, 0, 8)
                    }

                    amountAndCategory.put(radioString, transAmount)

                    tableLayout.addView(row)
                    mainLayout.removeView(transPopUp)
                    transactionActivity.addView(floatingActionButton)
                }
            }
        }
    }
}