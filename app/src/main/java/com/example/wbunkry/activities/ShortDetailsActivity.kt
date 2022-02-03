package com.example.wbunkry.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wbunkry.R
import com.example.wbunkry.adapters.PoiAdapter
import com.example.wbunkry.databinding.ActivityShortDetailsBinding

//import com.example.wbunkry.adapters.DaoAdapter

class ShortDetailsActivity : AppCompatActivity() {
    //private lateinit var viewModel: PoiViewModel
   // private lateinit var daoAdapter: DaoAdapter
    private lateinit var poiAdapter : PoiAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityShortDetailsBinding
   // private lateinit var listOfPois: LiveData<List<Poi>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_short_details)


       // viewModel = ViewModelProvider.AndroidViewModelFactory
         //   .getInstance(application)
           // .create(PoiViewModel::class.java)

//
    recyclerView = findViewById(R.id.recView)
        recyclerView.layoutManager = LinearLayoutManager(this)

      //  listOfPois = viewModel.getAllPois()
        //listOfPois.observe(this, Observer {
          //  if(it.isNotEmpty()){
            //    daoAdapter = DaoAdapter(it)
       // private val poiAdapter = PoiAdapter()
        poiAdapter=PoiAdapter(applicationContext)
                recyclerView.adapter = poiAdapter
            }
        //})

  //  }

    fun openShortPath (view: View) {
        val startShortPath = Intent(this, ShortPathActivity::class.java).apply {}
        startActivity(startShortPath)
    }
}