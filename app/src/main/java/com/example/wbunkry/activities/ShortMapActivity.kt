package com.example.wbunkry.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.wbunkry.R
import com.google.android.gms.maps.GoogleMap.OnPolygonClickListener
import com.google.android.gms.maps.GoogleMap.OnPolylineClickListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.wbunkry.databinding.ActivityShortMapBinding
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions






class ShortMapActivity : AppCompatActivity(), OnMapReadyCallback, OnPolylineClickListener, OnPolygonClickListener

{



    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityShortMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShortMapBinding.inflate(layoutInflater)
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
                .snippet("Krótki opis pierwszego obiektu" +
                        "\ndalsza część opisu" +
                        "\nrok powstania/dane techniczne \ntu będzie zdjęcie")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))

        )

        val sablLatLng = LatLng(54.59801, 18.80839)
        mMap.addMarker(
            MarkerOptions()
                .position(sablLatLng)
                .title("Schron amunicyjny Baterii Laskowskiego")
                .snippet("Opis drugiego obiektu")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))

        )

        val maLatLng = LatLng(54.59716, 18.80863)
        mMap.addMarker(
            MarkerOptions()
                .position(maLatLng)
                .title("Magazyn amunicji")
                .snippet("Opis trzeciego obiektu")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))


        )

        val cablLatLng = LatLng(54.59655, 18.81002)
        mMap.addMarker(
            MarkerOptions()
                .position(cablLatLng)
                .title("Centrala artyleryjska Baterii Laskowskiego")
                .snippet("Opis czwartego obiektu")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        )

        val mtLatLng = LatLng(54.59601, 18.81106)
        mMap.addMarker(
            MarkerOptions()
                .position(mtLatLng)
                .title("Morskie Tajemnice")
                .snippet("Opis piątego obiektu")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        )

        val stoLatLng = LatLng(54.59552, 18.81011)
        mMap.addMarker(
            MarkerOptions()
                .position(stoLatLng)
                .title("Stanowisko ogniowe")
                .snippet("...")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        )

        val soLatLng = LatLng(54.59477, 18.80897)
        mMap.addMarker(
            MarkerOptions()
                .position(soLatLng)
                .title("Stanowisko ogniowe")
                .snippet("...")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        )

        val bpLatLng = LatLng(54.59374, 18.81233)
        mMap.addMarker(
            MarkerOptions()
                .position(bpLatLng)
                .title("Bateria przeciwlotnicza")
                .snippet("...")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        )

        val stogLatLng = LatLng(54.59604, 18.81244)
        mMap.addMarker(
            MarkerOptions()
                .position(stogLatLng)
                .title("Stanowisko ogniowe")
                .snippet("...")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        )



        val shortPolyline = mMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    mbLatLng, sablLatLng, maLatLng, cablLatLng, mtLatLng, stoLatLng,
                    soLatLng, bpLatLng, stogLatLng

                )
        )

        val shortPolygon = googleMap.addPolygon(
            PolygonOptions()
            .clickable(true)
            .add(
                LatLng(54.598219, 18.805957),
                LatLng(54.59321, 18.807995),
                LatLng(54.593022, 18.812137),
                LatLng(54.598492, 18.820634),
                LatLng(54.601053, 18.815634)))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(54.59680, 18.81296), 15f))
        mMap.setOnPolylineClickListener(this)
        mMap.setOnPolygonClickListener(this)

        mMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(this))

    }

    override fun onPolylineClick(polyline: Polyline) {

    }

    /**
     * Listens for clicks on a polygon.
     * @param polygon The polygon object that the user has clicked.
     */
    override fun onPolygonClick(polygon: Polygon) {
        // Flip the values of the red, green, and blue components of the polygon's color.
        var color = polygon.strokeColor xor 0x00ffffff
        polygon.strokeColor = color
        color = polygon.fillColor xor 0x00ffffff
        polygon.fillColor = color
        val showShortDetails = Intent (this, ShortDetailsActivity::class.java).apply{}
        startActivity(showShortDetails)
    }
}


class CustomInfoWindowForGoogleMap(context: Context) : GoogleMap.InfoWindowAdapter {

    var mContext = context
    var mWindow = (context as Activity).layoutInflater.inflate(R.layout.iwin_layout, null)

    private fun renderWindowText(marker: Marker, view: View){

        val tvTitle = view.findViewById<TextView>(R.id.title)
        val tvSnippet = view.findViewById<TextView>(R.id.snippet)

        tvTitle.text = marker.title
        tvSnippet.text = marker.snippet


    }

    override fun getInfoContents(marker: Marker): View {
        renderWindowText(marker, mWindow)
        return mWindow
    }

    override fun getInfoWindow(marker: Marker): View? {
        renderWindowText(marker, mWindow)
        return mWindow
    }
}