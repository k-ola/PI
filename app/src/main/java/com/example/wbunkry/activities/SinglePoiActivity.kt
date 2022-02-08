package com.example.wbunkry.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.wbunkry.R
import com.example.wbunkry.database.Pois
import com.example.wbunkry.database.ShortPathDB

class SinglePoiActivity : AppCompatActivity() {
    private  lateinit var poi: Pois



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val position = intent.getIntExtra("position", 0)
        poi = ShortPathDB.poiList.get(position)
       setContentView(R.layout.single_poi)


        val namePoi = findViewById<TextView>(R.id.namePoi)
        namePoi.text=poi.name

            //(ShortPathDB.poiList[position].name)

        val latPoi = findViewById<TextView>(R.id.latPoi)
        latPoi.text=poi.lat
            //(ShortPathDB.poiList[position].lat)

        val lngPoi = findViewById<TextView>(R.id.lngPoi)
        lngPoi.text=poi.lng
            //(ShortPathDB.poiList[position].lng)

        val descPoi = findViewById<TextView>(R.id.Desc)
        descPoi.text=poi.desc
           // (ShortPathDB.poiList[position].desc)

    }



}