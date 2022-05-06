package com.example.courseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val actionbar = supportActionBar

        actionbar?.setDisplayHomeAsUpEnabled(true)
    }
}