package com.example.wbunkry.activities

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


class ShortDetailsActivity : AppCompatActivity(), PoiAdapter.OnItemClickListener {

    private lateinit var poiAdapter : PoiAdapter
    private lateinit var recyclerView: RecyclerView
    //private lateinit var binding: ActivityShortDetailsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_short_details)

        recyclerView = findViewById(R.id.recView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        poiAdapter=PoiAdapter(applicationContext, this)
        recyclerView.adapter = poiAdapter
            }

    override fun onItemClick(
       // item: ShortPathDB,
        position: Int) {
        val openPoiDetails = Intent(this, SinglePoiActivity::class.java)
       //  intent.putExtra("POINAME", item.poiNameList)
         //intent.putExtra("POILAT", item.poiLatList)
         //intent.putExtra("POILNG", item.poiLngList)
        //intent.putExtra("POIDESC", item.poiDescList)
           .apply {}
      startActivity(openPoiDetails)
    }

    fun openShortPath (view: View) {
        val startShortPath = Intent(this, ShortPathActivity::class.java).apply {}
        startActivity(startShortPath)
    }
}