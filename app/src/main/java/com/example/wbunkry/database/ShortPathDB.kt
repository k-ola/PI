package com.example.wbunkry.database

import android.graphics.Point

object ShortPathDB {

    val poiList: ArrayList<Pois> = arrayListOf<Pois>(
        Pois("poi1","59.888888","18.656989","desc1"),
        Pois("poi2","59.786700","18.569856","desc2"),
        Pois("poi3","58.888901", "18.482521","desc3")

            )
}

data class Pois(
    val name: String,
    val lat: String,
    val lng: String,
    val desc: String
)