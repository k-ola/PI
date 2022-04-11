package com.example.wbunkry.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.wbunkry.R
import com.example.wbunkry.database.LongPathDB
import com.example.wbunkry.database.LongPois
import com.example.wbunkry.database.MediumPathDB
import com.example.wbunkry.database.MediumPois
import java.io.IOException
import java.io.InputStream

class LongSinglePoiActivity : AppCompatActivity() {
    private  lateinit var longPoi: LongPois



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val position = intent.getIntExtra("position", 0)
        longPoi = LongPathDB.poiListLong[position]
        setContentView(R.layout.long_single_poi)

        val imgPoi = findViewById<ImageView>(R.id.imgPoiLong)
        val picName = longPoi.img

        val bitmap: Bitmap? = assetsToBitmap(picName)
        bitmap?.apply {
            imgPoi.setImageBitmap(this)

        }


        val namePoi = findViewById<TextView>(R.id.namePoiLong)
        namePoi.text=longPoi.name


        val descPoi = findViewById<TextView>(R.id.DescLong)
        descPoi.text=longPoi.desc


    }
}

