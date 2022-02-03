package com.example.wbunkry.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.wbunkry.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun openHistory (view : View) {
        val showHistory = Intent(this, HistoryActivity::class.java).apply{}
        startActivity(showHistory)
    }

    fun openPathways (view: View) {
        val showPathways = Intent (this, PathwaysActivity::class.java).apply{}
        startActivity(showPathways)
    }

}