package com.example.wbunkry.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.wbunkry.R
import com.example.wbunkry.database.Legend



class PinAdapter (private val context : Activity, private val arrayList : ArrayList<Legend>) : ArrayAdapter<Legend>(context,
        R.layout.list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null)

        val pointIcon : ImageView = view.findViewById(R.id.pointIcon)
        val pointDescription : TextView = view.findViewById(R.id.pointDescription)

        pointIcon.setImageResource(arrayList[position].iconId)
        pointDescription.text = arrayList[position].description

        return view
    }


}
