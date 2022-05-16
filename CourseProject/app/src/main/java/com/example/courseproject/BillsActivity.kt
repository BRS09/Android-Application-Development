package com.example.courseproject

import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Color.DKGRAY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_bills.*
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.add_bill.*
import kotlinx.android.synthetic.main.add_bill.creditBillRadioButton
import kotlinx.android.synthetic.main.add_bill.subscriptBillRadioButton
import kotlinx.android.synthetic.main.add_bill.transportBillRadioButton
import kotlinx.android.synthetic.main.add_bill.utilitiesBillRadioButton
import kotlinx.android.synthetic.main.add_transaction.*
import java.text.DecimalFormat

class BillsActivity : AppCompatActivity() {
    private var mDbAdapter: MyDBHandler? = null
    private lateinit var adapter: MyRecyclerViewAdapter
    private var totalOfBills: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills)
        val mainLayout: ConstraintLayout = findViewById(R.id.billsActivity)
        val decimalFormat = DecimalFormat("$##,###.##")
        val actionbar = supportActionBar

        initializeDatabase()
        loadList()

        val billsPopUp: View = LayoutInflater.from(this).inflate(R.layout.add_bill, mainLayout, false)

        actionbar?.setDisplayHomeAsUpEnabled(true)

        billsActionButton.setOnClickListener {
            billsActivity.removeAllViews()
            mainLayout.addView(billsPopUp)

                addBillButton?.setOnClickListener {
                    var billCategorySelected: String = ""
                    totalOfBills = totalOfBills.plus(costOfBill.text.toString().toDouble())

                    when(billCategoryRadioGroup.checkedRadioButtonId){
                        phoneBillRadioButton.id -> billCategorySelected = "Phone"
                        utilitiesBillRadioButton.id -> billCategorySelected = "Utilities"
                        transportBillRadioButton.id -> billCategorySelected = "Transportation"
                        subscriptBillRadioButton.id -> billCategorySelected = "Subscription"
                        creditBillRadioButton.id -> billCategorySelected = "Credit Card"
                        personalBillRadioButton.id -> billCategorySelected = "Personal"
                    }

                    var userBill: UserBill = UserBill(billName.text.toString(), costOfBill.text.toString().toDouble(), billCategorySelected, billDueDate.text.toString())

                    mDbAdapter?.insertBill(userBill.transactionName, userBill.transactionPrice, userBill.transactionCategory, userBill.transactionDate)

                    mainLayout.removeView(billsPopUp)
                    billsActivity.addView(billsActionButton)
                    recreate()
                }
        }
    }

    private fun initializeDatabase(){
        mDbAdapter = MyDBHandler(this@BillsActivity)
        mDbAdapter?.open()
    }

    private fun loadList() {
        val allBills = mDbAdapter?.selectAllBills()
        val listItems = arrayOfNulls<String>(allBills!!.size)
        var tableRow = TableRow(this)

        for (i in allBills.indices){
            listItems[i] = allBills[i]
        }

        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewTwo)
        var numberOfColumns: Int = 4
        adapter = MyRecyclerViewAdapter(listItems, totalOfBills, billsTotal)
        recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerView.adapter = adapter

        var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT)
        params.weight = 1.0f

    }
}