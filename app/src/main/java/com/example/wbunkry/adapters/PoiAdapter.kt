package com.example.wbunkry.adapters

import android.content.Context
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
import com.example.wbunkry.database.ShortPathDB

class PoiAdapter(private var context: Context, private val listener: OnItemClickListener):
    RecyclerView.Adapter<PoiAdapter.PoiViewHolder>() {



    override fun onCreateViewHolder(viewGrop: ViewGroup, p1: Int): PoiViewHolder {
     //create an object form .xml file
        val layoutInflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val poiRow = layoutInflater.inflate(R.layout.poi_row, viewGrop, false)


        return PoiViewHolder(poiRow)

    }
    override fun getItemCount(): Int {
        return ShortPathDB.poiList.size

    }

    override fun onBindViewHolder(holder: PoiViewHolder, position: Int) {
        val namePoi = holder.itemView.findViewById<TextView>(R.id.poiName)
        namePoi.text=(ShortPathDB.poiList[position].name)

    }


    inner class PoiViewHolder(val view: View) :RecyclerView.ViewHolder(view), View.OnClickListener {
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

