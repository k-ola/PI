package com.example.wbunkry.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wbunkry.R
import com.example.wbunkry.activities.ShortDetailsActivity
import com.example.wbunkry.activities.SinglePoiActivity
import com.example.wbunkry.database.MediumPathDB
import com.example.wbunkry.activities.MediumDetailsActivity
import com.example.wbunkry.activities.MediumSinglePoiActivity
import com.example.wbunkry.adapters.LongPoiAdapter.LongPoiViewHolder
import com.example.wbunkry.database.LongPathDB

class LongPoiAdapter(private var context: Context, private val listener: OnItemClickListener):
    RecyclerView.Adapter<LongPoiViewHolder>() {



    override fun onCreateViewHolder(viewGrop: ViewGroup, p1: Int): LongPoiViewHolder {
        //create an object form .xml file
        val layoutInflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val longPoiRow = layoutInflater.inflate(R.layout.poi_row, viewGrop, false)


        return LongPoiViewHolder(longPoiRow)

    }
    override fun getItemCount(): Int {
        return LongPathDB.poiListLong.size

    }

    override fun onBindViewHolder(holder: LongPoiViewHolder, position: Int) {
        val longNamePoi = holder.itemView.findViewById<TextView>(R.id.poiName)
        longNamePoi.text=(LongPathDB.poiListLong[position].name)
        longNamePoi.setTextColor(Color.parseColor("#FFFFFF"));
        longNamePoi.setTextSize(20F)
    }


    inner class LongPoiViewHolder(val view: View) :RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position=adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }
    interface OnItemClickListener {
        fun onItemClick(position: Int) {

        }

    }
}

