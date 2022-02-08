package com.example.wbunkry.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wbunkry.R
import com.example.wbunkry.adapters.PoiAdapter
import com.example.wbunkry.database.ShortPathDB
import com.example.wbunkry.databinding.ActivityShortDetailsBinding
import java.text.FieldPosition


class ShortDetailsActivity : AppCompatActivity(), PoiAdapter.OnItemClickListener {

    private lateinit var poiAdapter : PoiAdapter
    private lateinit var recyclerView: RecyclerView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_short_details)

        recyclerView = findViewById(R.id.recView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        poiAdapter=PoiAdapter(applicationContext, this)
        recyclerView.adapter = poiAdapter

            }



    override fun onItemClick(
        position: Int) {
        val openPoiDetails = Intent(this, SinglePoiActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(openPoiDetails)
    }



    fun openShortPath (view: View) {
        val startShortPath = Intent(this, ShortPathActivity::class.java).apply {}
        startActivity(startShortPath)
    }
}