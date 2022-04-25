package com.example.wbunkry.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wbunkry.R
import com.example.wbunkry.adapters.LongPoiAdapter
import com.example.wbunkry.adapters.MedPoiAdapter
import com.example.wbunkry.adapters.PoiAdapter

class LongDetailsActivity : AppCompatActivity(),  LongPoiAdapter.OnItemClickListener {

    private lateinit var longPoiAdapter : LongPoiAdapter
    private lateinit var longRecyclerView: RecyclerView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_details)

        longRecyclerView = findViewById(R.id.longRecView)
        longRecyclerView.layoutManager = LinearLayoutManager(this)

        longPoiAdapter= LongPoiAdapter(applicationContext, this)
        longRecyclerView.adapter = longPoiAdapter

    }



    override fun onItemClick(
        position: Int) {
        val openLongPoiDetails = Intent(this, LongSinglePoiActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(openLongPoiDetails)
    }



    fun openLongPath (view: View) {
        val startLongPath = Intent(this, LongMapActivity::class.java).apply {}
        startActivity(startLongPath)
    }

}