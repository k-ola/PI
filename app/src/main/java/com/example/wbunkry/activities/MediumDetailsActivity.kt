package com.example.wbunkry.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wbunkry.R
import com.example.wbunkry.adapters.MedPoiAdapter
import com.example.wbunkry.adapters.PoiAdapter

class MediumDetailsActivity : AppCompatActivity(),  MedPoiAdapter.OnItemClickListener {

    private lateinit var medPoiAdapter : MedPoiAdapter
    private lateinit var medRecyclerView: RecyclerView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium_details)

        medRecyclerView = findViewById(R.id.medRecView)
        medRecyclerView.layoutManager = LinearLayoutManager(this)

        medPoiAdapter= MedPoiAdapter(applicationContext, this)
        medRecyclerView.adapter = medPoiAdapter

    }



    override fun onItemClick(
        position: Int) {
        val openMediumPoiDetails = Intent(this, MediumSinglePoiActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(openMediumPoiDetails)
    }



    fun openMediumPath (view: View) {
        val startMediumPath = Intent(this, MediumMapActivity::class.java).apply {}
        startActivity(startMediumPath)
    }

}