package com.example.courseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val actionbar = supportActionBar

        actionbar?.setDisplayHomeAsUpEnabled(true)
    }
}