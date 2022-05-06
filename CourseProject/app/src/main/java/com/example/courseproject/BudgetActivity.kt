package com.example.courseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_budget.*

class BudgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        println(TransactionActivity.amountAndCategory)
        var total = 0.0
        var i = 5

        TransactionActivity.amountAndCategory.map { (key, value) -> total = total.plus(value) }

        Thread(Runnable {
            while(progressBar2.progress < total){
                try {
                    progressBar2.progress = progressBar2.progress.plus(i)
                    Thread.sleep(100)
                } catch(e: InterruptedException){
                    e.printStackTrace()
                }
            }
        }).start()
    }
}