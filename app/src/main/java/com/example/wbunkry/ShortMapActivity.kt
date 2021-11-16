package com.example.wbunkry

import android.app.Activity
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.wbunkry.databinding.ActivityLongMapBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.wbunkry.databinding.ActivityShortMapBinding
import com.google.android.gms.maps.model.*







class ShortMapActivity : AppCompatActivity(), OnMapReadyCallback

{



    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityLongMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLongMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        // Add markers in Hel and move the camera
        val mbLatLng = LatLng(54.59864, 18.80836)
        mMap.addMarker(
            MarkerOptions()
                .position(mbLatLng)
                .title("Magazyn 27 BAS")
                .snippet("Opis pierwszego obiektu")
        )

        val sablLatLng = LatLng(54.59801, 18.80839)
        mMap.addMarker(
            MarkerOptions()
                .position(sablLatLng)
                .title("Schron amunicyjny Baterii Laskowskiego")
                .snippet("Opis drugiego obiektu")
        )

        val maLatLng = LatLng(54.59716, 18.80863)
        mMap.addMarker(
            MarkerOptions()
                .position(maLatLng)
                .title("Magazyn amunicji")
                .snippet("Opis trzeciego obiektu")
        )

        val cablLatLng = LatLng(54.59655, 18.81002)
        mMap.addMarker(
            MarkerOptions()
                .position(cablLatLng)
                .title("Centrala artyleryjska Baterii Laskowskiego")
                .snippet("Opis czwartego obiektu")
        )

        val mtLatLng = LatLng(54.59601, 18.81106)
        mMap.addMarker(
            MarkerOptions()
                .position(mtLatLng)
                .title("Morskie Tajemnice")
                .snippet("Opis piątego obiektu")
        )

        val stoLatLng = LatLng(54.59552, 18.81011)
        mMap.addMarker(
            MarkerOptions()
                .position(stoLatLng)
                .title("Stanowisko ogniowe")
                .snippet("...")
        )

        val soLatLng = LatLng(54.59477, 18.80897)
        mMap.addMarker(
            MarkerOptions()
                .position(soLatLng)
                .title("Stanowisko ogniowe")
                .snippet("...")
        )

        val bpLatLng = LatLng(54.59374, 18.81233)
        mMap.addMarker(
            MarkerOptions()
                .position(bpLatLng)
                .title("Bateria przeciwlotnicza")
                .snippet("...")
        )

        val stogLatLng = LatLng(54.59604, 18.81244)
        mMap.addMarker(
            MarkerOptions()
                .position(stogLatLng)
                .title("Stanowisko ogniowe")
                .snippet("...")
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(54.59680, 18.81106), 16f))

        val shortPolyline = mMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    mbLatLng, sablLatLng, maLatLng, cablLatLng, mtLatLng, stoLatLng,
                    soLatLng, bpLatLng, stogLatLng

                )
        )

        mMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(this))

    }
}

class CustomInfoWindowForGoogleMap(context: Context) : GoogleMap.InfoWindowAdapter {

    var mContext = context
    var mWindow = (context as Activity).layoutInflater.inflate(R.layout.iwin_layout, null)

    private fun rendowWindowText(marker: Marker, view: View){

        val tvTitle = view.findViewById<TextView>(R.id.title)
        val tvSnippet = view.findViewById<TextView>(R.id.snippet)

        tvTitle.text = marker.title
        tvSnippet.text = marker.snippet

    }

    override fun getInfoContents(marker: Marker): View {
        rendowWindowText(marker, mWindow)
        return mWindow
    }

    override fun getInfoWindow(marker: Marker): View? {
        rendowWindowText(marker, mWindow)
        return mWindow
    }
}