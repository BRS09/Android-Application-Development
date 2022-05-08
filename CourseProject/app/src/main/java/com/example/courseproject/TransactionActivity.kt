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
import kotlin.reflect.full.memberProperties

class TransactionActivity : AppCompatActivity() {
    companion object{
        var amountAndCategory: HashMap<String, Double> = HashMap()
    }
    lateinit var dbHandler: MyDBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        dbHandler = MyDBHandler(this, null, null, 1)

        var transaction: Transaction? = null
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
                        foodRadioButton.id -> transaction?.transactionCategory = "Food"
                        utilitiesRadioButton.id -> transaction?.transactionCategory = "Utilities"
                        transportRadioButton.id -> transaction?.transactionCategory = "Transportation"
                        entertainRadioButton.id -> transaction?.transactionCategory = "Entertainment"
                        shoppingRadioButton.id -> transaction?.transactionCategory = "Shopping"
                        personalRadioButton.id -> transaction?.transactionCategory = "Personal"
                    }

                    transaction?.transactionName = transactionName.text.toString()
                    transaction?.transactionPrice = transactionAmount.text.toString().toDouble()
                    transaction?.transactionTotal = transTotal
                    transaction?.transactionDate = transactionDate.text.toString()

                    val arr = arrayOf(transaction?.transactionName, transaction?.transactionPrice, transaction?.transactionCategory, transaction?.transactionDate)

                    for(n in arr){
                        val textView: TextView = TextView(this)
                        if(n == transactionAmount.text) {
                            textView.text = decimalFormat.format(n.toString().toDouble())
                        }
                        else textView.text = n.toString()
                        row.addView(textView, tableRowParams)
                        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textView.setPadding(0, 8, 0, 8)
                    }

                    amountAndCategory.put(radioString, transAmount)
                    dbHandler.addTransaction(transaction!!)

                    tableLayout.addView(row)
                    mainLayout.removeView(transPopUp)
                    transactionActivity.addView(floatingActionButton)
                }
            }
        }
    }

    fun showAllTransactions(view: View){
        var dbHandler = MyDBHandler(this, null, null, 1)
        var transactions = dbHandler.findTransactions()
        transactions.forEach{
            val row = TableRow(this)
            it::class.memberProperties.forEach{members ->
                var textView = TextView(this)
                textView.text = members.getter.toString()
                row.addView(textView)
            }
        }
    }
}