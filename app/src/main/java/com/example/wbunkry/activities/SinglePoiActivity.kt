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
import com.example.wbunkry.database.Pois
import com.example.wbunkry.database.ShortPathDB
import java.io.IOException
import java.io.InputStream

class SinglePoiActivity : AppCompatActivity() {
    private  lateinit var poi: Pois



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val position = intent.getIntExtra("position", 0)
        poi = ShortPathDB.poiList[position]
        setContentView(R.layout.single_poi)

        val imgPoi = findViewById<ImageView>(R.id.imgPoi)
        val picName = poi.img
        val bitmap: Bitmap? = assetsToBitmap(picName)
        bitmap?.apply {
            imgPoi.setImageBitmap(this)

        }




        val namePoi = findViewById<TextView>(R.id.namePoi)
        namePoi.text=poi.name


        val descPoi = findViewById<TextView>(R.id.Desc)
        descPoi.text=poi.desc


    }



}

fun ImageView.setImageAssets(context: Context, picName: String) {
    try {
        with(context.assets.open(picName)) {
            setImageBitmap(BitmapFactory.decodeStream(this))
        }
    } catch (e: IOException) {

    }
}

fun Context.assetsToBitmap(picName: String): Bitmap? {
    return try {
        with(assets.open(picName)) {
            BitmapFactory.decodeStream(this)
        }
    }catch (e: IOException) {null}
}