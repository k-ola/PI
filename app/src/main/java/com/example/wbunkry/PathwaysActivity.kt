package com.example.wbunkry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PathwaysActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pathways)
    }
    fun openShortPathway (view: View) {
        val showShortPathway = Intent (this, ShortMapActivity::class.java).apply{}
        startActivity(showShortPathway)
    }
    fun openMediumPathway (view: View) {
        val showMediumPathway = Intent (this, MediumMapActivity::class.java).apply{}
        startActivity(showMediumPathway)
    }

    fun openLongPathway (view: View) {
        val showLongPathway = Intent (this, LongMapActivity::class.java).apply{}
        startActivity(showLongPathway)
    }
}