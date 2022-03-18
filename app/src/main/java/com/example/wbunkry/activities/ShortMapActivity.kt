package com.example.wbunkry.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
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






class ShortMapActivity : AppCompatActivity(), OnMapReadyCallback, OnPolylineClickListener, OnPolygonClickListener, GoogleMap.OnCircleClickListener {


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
        val s01 = LatLng(54.59489, 18.80892)
        mMap.addMarker(
            MarkerOptions()
                .position(s01)
                .title("S01")
                .snippet("Stanowisko nr 1 baterii im. Heliodora Laskowskiego, zniszczone wybuchem w 1946 roku")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sremove_yellow))

        )

        val s201 = LatLng(54.59551, 18.81013)
        mMap.addMarker(
            MarkerOptions()
                .position(s201)
                .title("201")
                .snippet("Stanowisko nr 2 baterii im. H. Laskowskiego, adaptowane w 1949 roku dla 13. Baterii Artylerii Stałej. ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_yellow))

        )

        val s202 = LatLng(54.59596, 18.81126)
        mMap.addMarker(
            MarkerOptions()
                .position(s202)
                .title("202")
                .snippet("Stanowisko nr 3 baterii im. H. Laskowskiego, adaptowane w 1949 roku dla 13. Baterii Artylerii Stałej. Znajduje się na nim zachowana armata B-13 kal. 130 mm.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_yellow))


        )


        val s203 = LatLng(54.59601, 18.81244)
        mMap.addMarker(
            MarkerOptions()
                .position(s203)
                .title("203")
                .snippet("Stanowisko nr 4 baterii im. H. Laskowskiego, adaptowane w 1949 roku dla 13. Baterii Artylerii Stałej.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_yellow))
        )


        val s204 = LatLng(54.59624, 18.81400)
        mMap.addMarker(
            MarkerOptions()
                .position(s204)
                .title("204")
                .snippet("Stanowisko nr 4 13. Baterii Artylerii Stałej. Zbudowane w 1948 roku na wzór Baterii Laskowskiego.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )

        val s205 = LatLng(54.59846, 18.81132)
        mMap.addMarker(
            MarkerOptions()
                .position(s205)
                .title("205")
                .snippet("Centrala artyleryjska 13. BAS zbudowana w 1948 roku")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val s206 = LatLng(54.59929, 18.81285)
        mMap.addMarker(
            MarkerOptions()
                .position(s206)
                .title("206")
                .snippet("Schron z wieżą radarową 13. BAS zbudowany w 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val s207 = LatLng(54.59914, 18.81325)
        mMap.addMarker(
            MarkerOptions()
                .position(s207)
                .title("207")
                .snippet("Główny Punkt Kierowania Ogniem 13. BAS zbudowany w latach 1951- 1952")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val s208 = LatLng(54.59725, 18.81107)
        mMap.addMarker(
            MarkerOptions()
                .position(s208)
                .title("208")
                .snippet("Zapasowy Punkt Kierowania Ogniem z 1948 roku, nadbudowany na fundamencie poniemieckiego radaru Würzburg-Riese, współpracującego z baterią „Schleisen”")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val s209 = LatLng(54.59841, 18.81313)
        mMap.addMarker(
            MarkerOptions()
                .position(s209)
                .title("209")
                .snippet("Elektrownia 13. BAS zbudowana w 1949 roku")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val s211 = LatLng(54.59678, 18.81254)
        mMap.addMarker(
            MarkerOptions()
                .position(s211)
                .title("211")
                .snippet("Schron załogi 13. BAS z 1954 roku")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val s212 = LatLng(54.59570, 18.80941)
        mMap.addMarker(
            MarkerOptions()
                .position(s212)
                .title("212")
                .snippet("Schron załogi 13. BAS z 1954 roku")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val s213 = LatLng(54.59798, 18.80839)
        mMap.addMarker(
            MarkerOptions()
                .position(s213)
                .title("213")
                .snippet("Schron amunicyjny Baterii Laskowskiego z lat 30. XX wieku, adaptowany po wojnie dla 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_yellow))
        )

        val s1001 = LatLng(54.59596, 18.80753)
        mMap.addMarker(
            MarkerOptions()
                .position(s1001)
                .title("1001")
                .snippet("Stanowisko ogniowe dział kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Połączone z obiektami 1002, 1003 i 1004 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )

        val s1002 = LatLng(54.59561, 18.80815)
        mMap.addMarker(
            MarkerOptions()
                .position(s1002)
                .title("1002")
                .snippet("Stanowisko ogniowe dział kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Połączone z obiektami 1001, 1003 i 1004 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )

        val s1003 = LatLng(54.59527, 18.80871)
        mMap.addMarker(
            MarkerOptions()
                .position(s1003)
                .title("1003")
                .snippet("Stanowisko ogniowe dział kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Połączone z obiektami 1001, 1002 i 1004 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )

        val s1004 = LatLng(54.59492, 18.80929)
        mMap.addMarker(
            MarkerOptions()
                .position(s1004)
                .title("1004")
                .snippet("Stanowisko ogniowe dział kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Połączone z obiektami 1001, 1002 i 1003 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )


        val s1005 = LatLng(54.59654, 18.80977)
        mMap.addMarker(
            MarkerOptions()
                .position(s1005)
                .title("1005")
                .snippet("Centrala artyleryjska 27. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )


        val s1007 = LatLng(54.59629, 18.80935)
        mMap.addMarker(
            MarkerOptions()
                .position(s1007)
                .title("1007")
                .snippet("Punkt kierowania ogniem 27. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val s1017 = LatLng(54.59763, 18.80754)
        mMap.addMarker(
            MarkerOptions()
                .position(s1017)
                .title("1017")
                .snippet("Schron-garaż dla reflektora 27. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )


        val s1021 = LatLng(54.59649, 18.80999)
        mMap.addMarker(
            MarkerOptions()
                .position(s1021)
                .title("1021")
                .snippet("Przedwojenna centrala artyleryjska baterii Laskowskiego, adaptowana w 1956 na stanowisko dowodzenia 27. BAS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_yellow))
        )


        val s1022 = LatLng(54.59717, 18.80701)
        mMap.addMarker(
            MarkerOptions()
                .position(s1022)
                .title("1022")
                .snippet("Schron-garaż agregatu dla reflektora 27. BAS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val s1025 = LatLng(54.59623, 18.80892)
        mMap.addMarker(
            MarkerOptions()
                .position(s1025)
                .title("1025")
                .snippet("Przedwojenny schron agregatu, używany także w 27. BAS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_yellow))
        )

        val sK1 = LatLng(54.59790, 18.80795)
        mMap.addMarker(
            MarkerOptions()
                .position(sK1)
                .title("K1")
                .snippet("Żelbetowa kopuła dla km z Kompanijnego Rejonu Umocnionego 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val sK2 = LatLng(54.59793, 18.80968)
        mMap.addMarker(
            MarkerOptions()
                .position(sK2)
                .title("K2")
                .snippet("Żelbetowa kopuła dla km z Kompanijnego Rejonu Umocnionego 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val sK5 = LatLng(54.59499, 18.81346)
        mMap.addMarker(
            MarkerOptions()
                .position(sK5)
                .title("K5")
                .snippet("Żelbetowa kopuła dla km z Kompanijnego Rejonu Umocnionego 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val pOMW = LatLng(54.59661, 18.80781)
        mMap.addMarker(
            MarkerOptions()
                .position(pOMW)
                .title("Punkt obserwacyjny MW nr 25")
                .snippet("Obiekt wykorzystywany przez Marynarkę Wojenną")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.srec_green))
        )

        val pDziala = LatLng(54.59649, 18.81166)
        mMap.addMarker(
            MarkerOptions()
                .position(pDziala)
                .title("Podstawa działa")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )

        val m27BAS = LatLng(54.59862, 18.80843)
        mMap.addMarker(
            MarkerOptions()
                .position(m27BAS)
                .title("Magazyn 27. BAS")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )

        val kopiec = LatLng(54.59545, 18.80771)
        mMap.addMarker(
            MarkerOptions()
                .position(kopiec)
                .title("Kopiec Kaszubów")
                .snippet("Symboliczny \"Początek Polski\"")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.srec_green))
        )

        val bat1plot = LatLng(54.59503, 18.81244)


        val bat2plot = LatLng(54.59483, 18.81255)
        mMap.addMarker(
            MarkerOptions()
                .position(bat2plot)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_full_red))
        )


        val bat3plot = LatLng(54.59484, 18.81298)
        mMap.addMarker(
            MarkerOptions()
                .position(bat3plot)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )


        val bat4plot = LatLng(54.59460, 18.81243)
        mMap.addMarker(
            MarkerOptions()
                .position(bat4plot)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )


        val bat5plot = LatLng(54.59464, 18.81366)
        mMap.addMarker(
            MarkerOptions()
                .position(bat5plot)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )

        val bat6plot = LatLng(54.59423, 18.81366)
        mMap.addMarker(
            MarkerOptions()
                .position(bat6plot)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )

        val bat7plot = LatLng(54.59382, 18.81288)
        mMap.addMarker(
            MarkerOptions()
                .position(bat7plot)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )

        val bat8plot = LatLng(54.59391, 18.81218)
        mMap.addMarker(
            MarkerOptions()
                .position(bat8plot)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sdot_red))
        )


        val shortPolyline = mMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    m27BAS, sK1, s213, s1017, s1022, s1001, s1002, s1003, s1004,
                    s201, s212, s1025, s1007, s1005, s1021, s202, s203, s204, s211,
                    s209, s207, s206, s205, s208, m27BAS
                )

        )
/*
        val batPolygon = googleMap.addPolygon(
            PolygonOptions()
                .clickable(true)
                .add(
                    bat1plot, bat2plot, bat3plot, bat4plot, bat5plot, bat6plot, bat7plot, bat8plot
                )
        )
*/

        val batCircle = googleMap.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.594385, 18.812900))
                .radius(80.0)
                .strokeColor(Color.BLUE)
                .visible(true)

        )


        val bat13Circle = googleMap.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.597369, 18.811717))
                .radius(240.0)
                .strokeColor(Color.BLUE)
                .visible(true)
        )

        val bat27Circle = googleMap.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.595788, 18.808734))
                .radius(100.0)
                .strokeColor(Color.BLUE)
                .visible(true)
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(54.596034, 18.809927), 17f))
        mMap.setOnPolylineClickListener(this)
        mMap.setOnPolygonClickListener(this)
        mMap.setOnCircleClickListener(this)

        mMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(this))

    }

    override fun onPolylineClick(polyline: Polyline) {

    }

    override fun onCircleClick(circle: Circle) {
        val showBatPlotDetails = Intent(this, SinglePoiActivity::class.java).apply {
            // putExtra("position", position)
        }

        startActivity(showBatPlotDetails)
    }




    override fun onPolygonClick(polygon: Polygon) {
        /*
        // Flip the values of the red, green, and blue components of the polygon's color.
        var color = polygon.strokeColor xor 0x00ffffff
        polygon.strokeColor = color
        color = polygon.fillColor xor 0x00ffffff
        polygon.fillColor = color
       // val showShortDetails = Intent (this, ShortDetailsActivity::class.java).apply{}
        val showBatPlotDetails = Intent (this, SinglePoiActivity::class.java).apply{
          // putExtra("position", position)
        }

        startActivity(showBatPlotDetails) */
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