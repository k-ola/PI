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

class MedPoiAdapter(private var context: Context, private val listener: OnItemClickListener):
    RecyclerView.Adapter<MedPoiAdapter.MedPoiViewHolder>() {



    override fun onCreateViewHolder(viewGrop: ViewGroup, p1: Int): MedPoiViewHolder {
        //create an object form .xml file
        val layoutInflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val medPoiRow = layoutInflater.inflate(R.layout.poi_row, viewGrop, false)


        return MedPoiViewHolder(medPoiRow)

    }
    override fun getItemCount(): Int {
        return MediumPathDB.poiListMedium.size

    }

    override fun onBindViewHolder(holder: MedPoiViewHolder, position: Int) {
        val medNamePoi = holder.itemView.findViewById<TextView>(R.id.poiName)
        medNamePoi.text=(MediumPathDB.poiListMedium[position].name)
        medNamePoi.setTextColor(Color.parseColor("#FFFFFF"));
        medNamePoi.setTextSize(20F)
    }


    inner class MedPoiViewHolder(val view: View) :RecyclerView.ViewHolder(view), View.OnClickListener {
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

