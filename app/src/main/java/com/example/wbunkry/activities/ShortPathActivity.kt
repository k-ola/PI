package com.example.wbunkry.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wbunkry.BuildConfig
import com.example.wbunkry.R
import com.example.wbunkry.adapters.PinAdapter
import com.example.wbunkry.database.Legend
import com.example.wbunkry.database.Pois
import com.example.wbunkry.database.ShortPathDB
import com.example.wbunkry.databinding.ActivityShortPathBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class ShortPathActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnPolylineClickListener, GoogleMap.OnCircleClickListener {

    private var map: GoogleMap? = null
    private var cameraPosition: CameraPosition? = null

    // The entry point to the Places API.
    private lateinit var placesClient: PlacesClient

    // The entry point to the Fused Location Provider.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private val defaultLocation = LatLng(54.596034, 18.809927)
    private var locationPermissionGranted = false

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private var lastKnownLocation: Location? = null
    private val PATTERN_DASH_LENGTH = 30
    private val PATTERN_GAP_LENGTH = 30
    private val dot = Dot()
    private val dash = Dash(PATTERN_DASH_LENGTH.toFloat())
    private val gap = Gap(PATTERN_GAP_LENGTH.toFloat())
    private val patternDotted = Arrays.asList(dot, gap)
    private val patternDashed = Arrays.asList(dash, gap)
    private val patternMixed = Arrays.asList(dot, gap, dot, dash, gap)
    private lateinit var binding: ActivityShortPathBinding
    private lateinit var pinArrayList: ArrayList<Legend>
    private lateinit var poi: Pois
    private var clicked = false

    private lateinit var batCircle: Circle
    private lateinit var bat13Circle: Circle
    private lateinit var bat27Circle: Circle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val position = intent.getIntExtra("position", 0)
        poi = ShortPathDB.poiList.get(position)
        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION)
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION)
        }



        binding = ActivityShortPathBinding.inflate(layoutInflater)


        // Retrieve the content view that renders the map.


        setContentView(binding.root)

        val iconId = intArrayOf(
            R.drawable.sdot_full_yellow,
            R.drawable.vsdot_full_yellow,
            R.drawable.vsdot_yellow,
            R.drawable.vsremove_yellow,
            R.drawable.sdot_full_blue,
            R.drawable.vsdot_full_blue,
            R.drawable.vsdot_blue,
            R.drawable.vsremove_blue,
            R.drawable.sdot_full_red,
            R.drawable.vsdot_full_red,
            R.drawable.vsdot_red,
            R.drawable.vsremove_red,
            R.drawable.srec_green
        )

        val iconDescription = arrayOf(
            "FORTYFIKACJE POLSKIE Z LAT 1920-1939",
            "Schrony",
            "Działobitnie",
            "Obiekty w ruinie",
            "FORTYFIKACJE NIEMIECKIE Z LAT 1920-1939",
            "Schrony",
            "Działobitnie",
            "Obiekty w ruinie",
            "FORTYFIKACJE POLSKIE Z LAT 1946-1977",
            "Schrony",
            "Działobitnie",
            "Obiekty w ruinie",
            "INNE OBIEKTY"
        )

        pinArrayList = ArrayList()

        for (i in iconDescription.indices) {
            val icon = Legend(iconDescription[i], iconId[i])
            pinArrayList.add(icon)
        }


        val fab = findViewById<FloatingActionButton>(R.id.legendFab)
        fab.setOnClickListener {
            onLegendButtonClicked()
        }


        // Construct a PlacesClient
        Places.initialize(applicationContext, BuildConfig.MAPS_API_KEY)
        placesClient = Places.createClient(this)

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // Build the map.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }

    private fun onLegendButtonClicked() {
        setVisibility(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        binding.legendlist.adapter = PinAdapter(this, pinArrayList)
        if (!clicked) {

            binding.legendlist.visibility = View.VISIBLE
        } else {
            binding.legendlist.visibility = View.INVISIBLE
        }
    }


    /**
     * Saves the state of the map when the activity is paused.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        map?.let { map ->
            outState.putParcelable(KEY_CAMERA_POSITION, map.cameraPosition)
            outState.putParcelable(KEY_LOCATION, lastKnownLocation)
        }
        super.onSaveInstanceState(outState)
    }

    /**
     * Sets up the options menu.
     * @param menu The options menu.
     * @return Boolean.

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.current_place_menu, menu)
    return true
    }
     */
    /**
     * Handles a click on the menu option to get a place.
     * @param item The menu item to handle.
     * @return Boolean.

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.option_get_place) {
    showCurrentPlace()
    }
    return true
    }
     */
    /**
     * Manipulates the map when it's available.
     * This callback is triggered when the map is ready to be used.
     */
    override fun onMapReady(map: GoogleMap) {
        this.map = map


        // Use a custom info window adapter to handle multiple lines of text in the
        // info window contents.
        this.map?.setInfoWindowAdapter(object : InfoWindowAdapter {
            // Return null here, so that getInfoContents() is called next.
            override fun getInfoWindow(arg0: Marker): View? {
                return null
            }

            override fun getInfoContents(marker: Marker): View {
                // Inflate the layouts for the info window, title and snippet.
                val infoWindow = layoutInflater.inflate(
                    R.layout.custom_info_contents,
                    findViewById<FrameLayout>(R.id.map), false
                )
                val title = infoWindow.findViewById<TextView>(R.id.title)
                title.text = marker.title
                val snippet = infoWindow.findViewById<TextView>(R.id.snippet)
                snippet.text = marker.snippet
                return infoWindow
            }
        })

        // Prompt the user for permission.
        getLocationPermission()

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI()

        // Get the current location of the device and set the position of the map.
        getDeviceLocation()

        // Add markers in Hel and move the camera
        val s01 = LatLng(54.59489, 18.80892)
        map.addMarker(
            MarkerOptions()
                .position(s01)
                .title("S01")
                .snippet("Stanowisko nr 1 baterii im. Heliodora Laskowskiego, zniszczone wybuchem w 1946 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsremove_yellow))

        )

        val s201 = LatLng(54.59551, 18.81013)
        map.addMarker(
            MarkerOptions()
                .position(s201)
                .title("201")
                .snippet("Stanowisko nr 2 baterii im. H. Laskowskiego, adaptowane w 1949 roku dla 13. Baterii Artylerii Stałej. ")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow))

        )

        val s202 = LatLng(54.59596, 18.81126)
        map.addMarker(
            MarkerOptions()
                .position(s202)
                .title("202")
                .snippet("Stanowisko nr 3 baterii im. H. Laskowskiego, adaptowane w 1949 roku dla 13. Baterii Artylerii Stałej. Znajduje się na nim zachowana armata B-13 kal. 130 mm.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow))


        )


        val s203 = LatLng(54.59601, 18.81244)
        map.addMarker(
            MarkerOptions()
                .position(s203)
                .title("203")
                .snippet("Stanowisko nr 4 baterii im. H. Laskowskiego, adaptowane w 1949 roku dla 13. Baterii Artylerii Stałej.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow))
        )


        val s204 = LatLng(54.59624, 18.81400)
        map.addMarker(
            MarkerOptions()
                .position(s204)
                .title("204")
                .snippet("Stanowisko nr 4 13. Baterii Artylerii Stałej. Zbudowane w 1948 roku na wzór Baterii Laskowskiego.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val s205 = LatLng(54.59846, 18.81132)
        map.addMarker(
            MarkerOptions()
                .position(s205)
                .title("205")
                .snippet("Centrala artyleryjska 13. BAS zbudowana w 1948 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s206 = LatLng(54.59929, 18.81285)
        map.addMarker(
            MarkerOptions()
                .position(s206)
                .title("206")
                .snippet("Schron z wieżą radarową 13. BAS zbudowany w 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s207 = LatLng(54.59914, 18.81325)
        map.addMarker(
            MarkerOptions()
                .position(s207)
                .title("207")
                .snippet("Główny Punkt Kierowania Ogniem 13. BAS zbudowany w latach 1951- 1952")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s208 = LatLng(54.59725, 18.81107)
        map.addMarker(
            MarkerOptions()
                .position(s208)
                .title("208")
                .snippet("Zapasowy Punkt Kierowania Ogniem z 1948 roku, nadbudowany na fundamencie poniemieckiego radaru Würzburg-Riese, współpracującego z baterią „Schleisen”")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s209 = LatLng(54.59841, 18.81313)
        map.addMarker(
            MarkerOptions()
                .position(s209)
                .title("209")
                .snippet("Elektrownia 13. BAS zbudowana w 1949 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s211 = LatLng(54.59678, 18.81254)
        map.addMarker(
            MarkerOptions()
                .position(s211)
                .title("211")
                .snippet("Schron załogi 13. BAS z 1954 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s212 = LatLng(54.59570, 18.80941)
        map.addMarker(
            MarkerOptions()
                .position(s212)
                .title("212")
                .snippet("Schron załogi 13. BAS z 1954 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s213 = LatLng(54.59798, 18.80839)
        map.addMarker(
            MarkerOptions()
                .position(s213)
                .title("213")
                .snippet("Schron amunicyjny Baterii Laskowskiego z lat 30. XX wieku, adaptowany po wojnie dla 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow))
        )

        val s1001 = LatLng(54.59596, 18.80753)
        map.addMarker(
            MarkerOptions()
                .position(s1001)
                .title("1001")
                .snippet("Stanowisko ogniowe dział kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Połączone z obiektami 1002, 1003 i 1004 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val s1002 = LatLng(54.59561, 18.80815)
        map.addMarker(
            MarkerOptions()
                .position(s1002)
                .title("1002")
                .snippet("Stanowisko ogniowe dział kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Połączone z obiektami 1001, 1003 i 1004 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val s1003 = LatLng(54.59527, 18.80871)
        map.addMarker(
            MarkerOptions()
                .position(s1003)
                .title("1003")
                .snippet("Stanowisko ogniowe dział kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Połączone z obiektami 1001, 1002 i 1004 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val s1004 = LatLng(54.59492, 18.80929)
        map.addMarker(
            MarkerOptions()
                .position(s1004)
                .title("1004")
                .snippet("Stanowisko ogniowe dział kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Połączone z obiektami 1001, 1002 i 1003 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )


        val s1005 = LatLng(54.59654, 18.80977)
        map.addMarker(
            MarkerOptions()
                .position(s1005)
                .title("1005")
                .snippet("Centrala artyleryjska 27. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )


        val s1007 = LatLng(54.59629, 18.80935)
        map.addMarker(
            MarkerOptions()
                .position(s1007)
                .title("1007")
                .snippet("Punkt kierowania ogniem 27. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s1017 = LatLng(54.59763, 18.80754)
        map.addMarker(
            MarkerOptions()
                .position(s1017)
                .title("1017")
                .snippet("Schron-garaż dla reflektora 27. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )


        val s1021 = LatLng(54.59649, 18.80999)
        map.addMarker(
            MarkerOptions()
                .position(s1021)
                .title("1021")
                .snippet("Przedwojenna centrala artyleryjska baterii Laskowskiego, adaptowana w 1956 na stanowisko dowodzenia 27. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow))
        )


        val s1022 = LatLng(54.59717, 18.80701)
        map.addMarker(
            MarkerOptions()
                .position(s1022)
                .title("1022")
                .snippet("Schron-garaż agregatu dla reflektora 27. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s1025 = LatLng(54.59623, 18.80892)
        map.addMarker(
            MarkerOptions()
                .position(s1025)
                .title("1025")
                .snippet("Przedwojenny schron agregatu, używany także w 27. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow))
        )

        val sK1 = LatLng(54.59790, 18.80795)
        map.addMarker(
            MarkerOptions()
                .position(sK1)
                .title("K1")
                .snippet("Żelbetowa kopuła dla km z Kompanijnego Rejonu Umocnionego 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val sK2 = LatLng(54.59793, 18.80968)
        map.addMarker(
            MarkerOptions()
                .position(sK2)
                .title("K2")
                .snippet("Żelbetowa kopuła dla km z Kompanijnego Rejonu Umocnionego 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val sK5 = LatLng(54.59499, 18.81346)
        map.addMarker(
            MarkerOptions()
                .position(sK5)
                .title("K5")
                .snippet("Żelbetowa kopuła dla km z Kompanijnego Rejonu Umocnionego 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val pOMW = LatLng(54.59661, 18.80781)
        map.addMarker(
            MarkerOptions()
                .position(pOMW)
                .title("Punkt obserwacyjny MW nr 25")
                .snippet("Obiekt wykorzystywany przez Marynarkę Wojenną")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsrec_green))
        )

        val pDziala = LatLng(54.59649, 18.81166)
        map.addMarker(
            MarkerOptions()
                .position(pDziala)
                .title("Podstawa działa")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val m27BAS = LatLng(54.59862, 18.80843)
        map.addMarker(
            MarkerOptions()
                .position(m27BAS)
                .title("Magazyn 27. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val kopiec = LatLng(54.59545, 18.80771)
        map.addMarker(
            MarkerOptions()
                .position(kopiec)
                .title("Kopiec Kaszubów")
                .snippet("Symboliczny \"Początek Polski\"")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsrec_green))
        )

        val bat1plot = LatLng(54.59503, 18.81244)


        val bat2plot = LatLng(54.59483, 18.81255)
        map.addMarker(
            MarkerOptions()
                .position(bat2plot)
                .title("Schron amunicyjny")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )


        val bat3plot = LatLng(54.59484, 18.81298)
        map.addMarker(
            MarkerOptions()
                .position(bat3plot)
                .title("Działobitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )


        val bat4plot = LatLng(54.59460, 18.81243)
        map.addMarker(
            MarkerOptions()
                .position(bat4plot)
                .title("Działobitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )


        val bat5plot = LatLng(54.59464, 18.81366)
        map.addMarker(
            MarkerOptions()
                .position(bat5plot)
                .title("Działobitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val bat6plot = LatLng(54.59423, 18.81366)
        map.addMarker(
            MarkerOptions()
                .position(bat6plot)
                .title("Działobitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val bat7plot = LatLng(54.59382, 18.81288)
        map.addMarker(
            MarkerOptions()
                .position(bat7plot)
                .title("Działobitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val bat8plot = LatLng(54.59391, 18.81218)
        map.addMarker(
            MarkerOptions()
                .position(bat8plot)
                .title("Działobitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val lighthouse = LatLng(54.60005, 18.81286)
        map.addMarker(
            MarkerOptions()
                .position(lighthouse)
                .title("Latarnia morska")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsrec_green))
        )


        val shortPolyline = map.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    m27BAS, sK1, s213, s1017, s1022, s1001, s1002, s1003, s1004,
                    s201, s212, s1025, s1007, s1005, s1021, s202, s203, s204, s211,
                    s209, s207, s206, s205, s208, m27BAS
                )
                .pattern(patternDashed)

        )

        //Adding circles with markers - one circle is one of the main POIs
        batCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.594385, 18.812900))
                .radius(80.0)
                .strokePattern(patternDotted)
                .visible(true)
                .fillColor(0x2133ff33)


        )


        bat13Circle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.597369, 18.811717))
                .radius(240.0)
                .strokePattern(patternDotted)

                .visible(true)
                .fillColor(0x2133ff33)
        )

        bat27Circle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.595788, 18.808734))
                .radius(100.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)

        )

        map.setOnPolylineClickListener(this)
        map.setOnCircleClickListener(this)

        map.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(this))

    }

    override fun onPolylineClick(polyline: Polyline) {

    }

    override fun onCircleClick(circle: Circle) {
        val showBatPlotDetails = Intent(this, SinglePoiActivity::class.java).also {
            when (circle.id) {
                batCircle.id -> it.putExtra(
                    "position",
                    ShortPathDB.PoiIndex.BATERIA_PRZECIWLOTNICZEJ.ordinal
                )
                bat27Circle.id -> it.putExtra(
                    "position",
                    ShortPathDB.PoiIndex.BATERIA_STALEJ.ordinal
                )
                bat13Circle.id -> it.putExtra(
                    "position",
                    ShortPathDB.PoiIndex.BATERIA_NADBRZEZNEJ.ordinal
                )
            }
        }
        startActivity(showBatPlotDetails)
    }


    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            map?.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        lastKnownLocation!!.latitude,
                                        lastKnownLocation!!.longitude
                                    ), DEFAULT_ZOOM.toFloat()
                                )
                            )
                        }
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.")
                        Log.e(TAG, "Exception: %s", task.exception)
                        map?.moveCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat())
                        )
                        map?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }


    /**
     * Prompts the user for permission to use the device location.
     */
    private fun getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    locationPermissionGranted = true
                }
            }
        }
        updateLocationUI()
    }


    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    @SuppressLint("MissingPermission")
    private fun updateLocationUI() {
        if (map == null) {
            return
        }
        try {
            if (locationPermissionGranted) {
                map?.isMyLocationEnabled = true
                map?.uiSettings?.isMyLocationButtonEnabled = true
            } else {
                map?.isMyLocationEnabled = false
                map?.uiSettings?.isMyLocationButtonEnabled = false
                lastKnownLocation = null
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    companion object {
        private val TAG = ShortPathActivity::class.java.simpleName
        private const val DEFAULT_ZOOM = 16
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1

        // Keys for storing activity state.
        private const val KEY_CAMERA_POSITION = "camera_position"
        private const val KEY_LOCATION = "location"

        // Used for selecting the current place.
        private const val M_MAX_ENTRIES = 5
    }
}

class CustomInfoWindowForGoogleMap(context: Context) : GoogleMap.InfoWindowAdapter {

    var mContext = context
    var mWindow =
        (context as Activity).layoutInflater.inflate(com.example.wbunkry.R.layout.iwin_layout, null)

    private fun renderWindowText(marker: Marker, view: View) {

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

