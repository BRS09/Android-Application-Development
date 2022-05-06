package com.example.courseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_bills.*
import kotlinx.android.synthetic.main.add_bill.*
import kotlinx.android.synthetic.main.add_bill.creditBillRadioButton
import kotlinx.android.synthetic.main.add_bill.subscriptBillRadioButton
import kotlinx.android.synthetic.main.add_bill.transportBillRadioButton
import kotlinx.android.synthetic.main.add_bill.utilitiesBillRadioButton
import kotlinx.android.synthetic.main.add_transaction.*
import java.text.DecimalFormat

class BillsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills)

        val decimalFormat = DecimalFormat("$##,###.##")
        val mainLayout: ConstraintLayout = findViewById(R.id.billsActivity)
        var totalOfBills = 0.0
        val actionbar = supportActionBar

        val billsPopUp: View = LayoutInflater.from(this).inflate(R.layout.add_bill, mainLayout, false)
        billsPopUp.setBackgroundColor(ContextCompat.getColor(this, androidx.appcompat.R.color.tooltip_background_light))

        actionbar?.setDisplayHomeAsUpEnabled(true)

        billsActionButton.setOnClickListener {
            mainLayout.addView(billsPopUp)
            billsActivity.removeView(billsActionButton)

            if(addBillButton.isVisible){
                addBillButton?.setOnClickListener {
                    val row: TableRow = TableRow(this)
                    val tableRowParams: TableRow.LayoutParams = TableRow.LayoutParams (TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f)
                    val billName = billName.text
                    val billCategory = billCategoryRadioGroup.checkedRadioButtonId
                    var billCategorySelected: String? = null
                    val billCost = costOfBill.text.toString()
                    val billDueDate = billDueDate.text
                    totalOfBills = totalOfBills.plus(billCost.toDouble())
                    billsTotal.text = decimalFormat.format(totalOfBills)

                    when(billCategory){
                        phoneBillRadioButton.id -> billCategorySelected = "Phone"
                        utilitiesBillRadioButton.id -> billCategorySelected = "Utilities"
                        transportBillRadioButton.id -> billCategorySelected = "Transportation"
                        subscriptBillRadioButton.id -> billCategorySelected = "Subscription"
                        creditBillRadioButton.id -> billCategorySelected = "Credit Card"
                        personalBillRadioButton.id -> billCategorySelected = "Personal"
                    }

                    val billsArr = arrayOf(billName, billCost, billCategorySelected, billDueDate)

                    for(n in billsArr){
                        val billTextView: TextView = TextView(this)
                        if(n == billCost) billTextView.text = decimalFormat.format(n.toString().toDouble())
                        else billTextView.text = n
                        row.addView(billTextView, tableRowParams)
                        billTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        billTextView.setPadding(0, 8, 0, 8)
                    }

                    tableLayout.addView(row)
                    mainLayout.removeView(billsPopUp)
                    billsActivity.addView(billsActionButton)
                }
            }
        }
    }
}