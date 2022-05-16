package com.example.courseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.add_transaction.*
import java.text.DecimalFormat

class TransactionActivity : AppCompatActivity() {
    private var mDbAdapter: MyDBHandler? = null
    private lateinit var adapter: MyRecyclerViewAdapter
    private var transTotal: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        val decimalFormat = DecimalFormat("$##,###.##")
        initializeDatabase()
        loadList()
        transactionTotal.text = decimalFormat.format(transTotal)

        val mainLayout: ConstraintLayout = findViewById(R.id.transactionActivity)
        val actionbar = supportActionBar
        var radioString: String = ""

        val transPopUp: View = LayoutInflater.from(this).inflate(R.layout.add_transaction, mainLayout, false)

        actionbar?.setDisplayHomeAsUpEnabled(true)

        floatingActionButton.setOnClickListener {
            transactionActivity.removeAllViews()
            mainLayout.addView(transPopUp)

            if(addTransactionButton.isVisible){
                addTransactionButton?.setOnClickListener {
                    transTotal = transTotal.plus(transactionAmount.text.toString().toDouble())

                    when(transCategoryRadioGroup.checkedRadioButtonId){
                        foodRadioButton.id -> radioString = "Food"
                        utilitiesRadioButton.id -> radioString = "Utilities"
                        transportRadioButton.id -> radioString = "Transportation"
                        entertainRadioButton.id -> radioString = "Entertainment"
                        shoppingRadioButton.id -> radioString = "Shopping"
                        personalRadioButton.id -> radioString = "Personal"
                    }

                    var userTransaction: UserTransaction = UserTransaction(transactionName.text.toString(), transactionAmount.text.toString().toDouble(), radioString, transactionDate.text.toString())

                    mDbAdapter?.insertTransaction(userTransaction.transactionName, userTransaction.transactionPrice, userTransaction.transactionCategory, userTransaction.transactionDate)

                    mainLayout.removeView(transPopUp)
                    transactionActivity.addView(floatingActionButton)
                    recreate()
                }
            }
        }
    }

    private fun initializeDatabase(){
        mDbAdapter = MyDBHandler(this@TransactionActivity)
        mDbAdapter?.open()
    }

    private fun loadList() {
        val allTransactions = mDbAdapter?.selectAllTransactions()
        val listItems = arrayOfNulls<String>(allTransactions!!.size)
        var tableRow = TableRow(this)

        for (i in allTransactions.indices){
            listItems[i] = allTransactions[i]
        }

        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewOne)
        var numberOfColumns: Int = 4
        adapter = MyRecyclerViewAdapter(listItems, transTotal, transactionTotal)
        recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerView.adapter = adapter

        var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT)
        params.weight = 1.0f

    }
}