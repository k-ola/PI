package com.example.wbunkry.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.Manifest
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wbunkry.BuildConfig
import com.example.wbunkry.R
import com.example.wbunkry.adapters.PinAdapter
import com.example.wbunkry.database.Legend
import com.example.wbunkry.database.LongPathDB
import com.example.wbunkry.database.MediumPathDB
import com.example.wbunkry.databinding.ActivityLongMapBinding
import com.example.wbunkry.databinding.ActivityMediumMapBinding
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
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class LongMapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener, GoogleMap.OnCircleClickListener {

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
    private var likelyPlaceNames: Array<String?> = arrayOfNulls(0)
    private var likelyPlaceAddresses: Array<String?> = arrayOfNulls(0)
    private var likelyPlaceAttributions: Array<List<*>?> = arrayOfNulls(0)
    private var likelyPlaceLatLngs: Array<LatLng?> = arrayOfNulls(0)
    private val PATTERN_DASH_LENGTH = 30
    private val PATTERN_GAP_LENGTH = 30
    private val dot = Dot()
    private val dash = Dash(PATTERN_DASH_LENGTH.toFloat())
    private val gap = Gap(PATTERN_GAP_LENGTH.toFloat())
    private val patternDotted = Arrays.asList(dot, gap)
    private val patternDashed = Arrays.asList(dash, gap)
    private val patternMixed = Arrays.asList(dot, gap, dot, dash, gap)
    private lateinit var binding : ActivityLongMapBinding
    private lateinit var pinArrayList: ArrayList<Legend>

    private var clicked = false


    private lateinit var bat1Circle: Circle
    private lateinit var bat13Circle: Circle
    private lateinit var bat27Circle: Circle
    private lateinit var bat21Circle: Circle
    private lateinit var batdywCircle: Circle
    private lateinit var batPlotHelCircle: Circle
    private lateinit var batRu2aCircle: Circle
    private lateinit var batRu2bCircle: Circle
    private lateinit var batRu2cCircle: Circle
    private lateinit var batRu2dCircle: Circle
    private lateinit var batRu2abcCircle: Circle
    private lateinit var dywWLOPCircle: Circle
    private lateinit var mowCircle: Circle
    private lateinit var mow9eCircle: Circle
    private lateinit var elektrownia_RUHCircle: Circle
    private lateinit var pktObsCircle: Circle
    private lateinit var dunskaCircle: Circle
    private lateinit var batPlotCircle: Circle
    private lateinit var szwedzkaCircle: Circle
    private lateinit var prawyPObsCircle: Circle
    private lateinit var batplot115Circle: Circle
    private lateinit var bas3Circle: Circle
    private lateinit var batart34Circle: Circle
    private lateinit var pprrCircle: Circle
    private lateinit var batGreckaCircle: Circle
    private lateinit var magtorpCircle: Circle
    private lateinit var  magminCircle: Circle
    private lateinit var zbpaliwCircle: Circle
    private lateinit var batplotborCircle: Circle
    private lateinit var batplot23Circle: Circle
    private lateinit var dywrak34Circle: Circle
    private lateinit var lpo3basCircle: Circle
    private lateinit var magamunCircle: Circle
    private lateinit var batplot22Circle: Circle
    private lateinit var bat60plotCircle: Circle
    private lateinit var nukschronCircle: Circle
    private lateinit var oojastCircle: Circle
    private lateinit var batplotjastCircle: Circle
    private lateinit var wrakiCircle: Circle



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION)
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION)
        }

        binding = ActivityLongMapBinding.inflate(layoutInflater)


        // Retrieve the content view that renders the map.
        //setContentView(R.layout.activity_medium_map)

        setContentView(binding.root)

        val iconId = intArrayOf(
            R.drawable.sdot_full_yellow,R.drawable.vsdot_full_yellow, R.drawable.vsdot_yellow, R.drawable.vsremove_yellow,
            R.drawable.sdot_full_blue, R.drawable.vsdot_full_blue, R.drawable.vsdot_blue, R.drawable.vsremove_blue,
            R.drawable.sdot_full_red, R.drawable.vsdot_full_red, R.drawable.vsdot_red, R.drawable.vsremove_red,
            R.drawable.srec_green, R.drawable.vsdot_grey)

        val iconDescription = arrayOf(
            "FORTYFIKACJE POLSKIE Z LAT 1920-1939",
            "Schrony",
            "Dzia??obitnie",
            "Obiekty w ruinie",
            "FORTYFIKACJE NIEMIECKIE Z LAT 1920-1939",
            "Schrony",
            "Dzia??obitnie",
            "Obiekty w ruinie",
            "FORTYFIKACJE POLSKIE Z LAT 1946-1977",
            "Schrony",
            "Dzia??obitnie",
            "Obiekty w ruinie",
            "INNE OBIEKTY",
            "OBIEKTY NIEISTNIEJ??CE"
        )

        pinArrayList = ArrayList()

        for( i in iconDescription.indices) {
            val icon = Legend(iconDescription[i], iconId[i])
            pinArrayList.add(icon)
        }




        val fab = findViewById<FloatingActionButton>(R.id.legendFab)
        fab.setOnClickListener{
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
        if(!clicked) {

            binding.legendlist.visibility = View.VISIBLE
        }
        else{
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

        map.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(defaultLocation, LongMapActivity.DEFAULT_ZOOM.toFloat())
        )


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
                    findViewById<FrameLayout>(R.id.map), false)
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
                .snippet("Stanowisko nr 2 baterii im. H. Laskowskiego, adaptowane w 1949 roku dla 13. Baterii Artylerii Sta??ej. ")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow))

        )

        val s202 = LatLng(54.59596, 18.81126)
        map.addMarker(
            MarkerOptions()
                .position(s202)
                .title("202")
                .snippet("Stanowisko nr 3 baterii im. H. Laskowskiego, adaptowane w 1949 roku dla 13. Baterii Artylerii Sta??ej. Znajduje si?? na nim zachowana armata B-13 kal. 130 mm.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow))


        )


        val s203 = LatLng(54.59601, 18.81244)
        map.addMarker(
            MarkerOptions()
                .position(s203)
                .title("203")
                .snippet("Stanowisko nr 4 baterii im. H. Laskowskiego, adaptowane w 1949 roku dla 13. Baterii Artylerii Sta??ej.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow))
        )


        val s204 = LatLng(54.59624, 18.81400)
        map.addMarker(
            MarkerOptions()
                .position(s204)
                .title("204")
                .snippet("Stanowisko nr 4 13. Baterii Artylerii Sta??ej. Zbudowane w 1948 roku na wz??r Baterii Laskowskiego.")
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
                .snippet("Schron z wie???? radarow?? 13. BAS zbudowany w 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s207 = LatLng(54.59914, 18.81325)
        map.addMarker(
            MarkerOptions()
                .position(s207)
                .title("207")
                .snippet("G????wny Punkt Kierowania Ogniem 13. BAS zbudowany w latach 1951- 1952")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s208 = LatLng(54.59725, 18.81107)
        map.addMarker(
            MarkerOptions()
                .position(s208)
                .title("208")
                .snippet("Zapasowy Punkt Kierowania Ogniem z 1948 roku, nadbudowany na fundamencie poniemieckiego radaru W??rzburg-Riese, wsp????pracuj??cego z bateri?? ???Schleisen???")
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
                .snippet("Schron za??ogi 13. BAS z 1954 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s212 = LatLng(54.59570, 18.80941)
        map.addMarker(
            MarkerOptions()
                .position(s212)
                .title("212")
                .snippet("Schron za??ogi 13. BAS z 1954 roku")
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
                .snippet("Stanowisko ogniowe dzia?? kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Po????czone z obiektami 1002, 1003 i 1004 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val s1002 = LatLng(54.59561, 18.80815)
        map.addMarker(
            MarkerOptions()
                .position(s1002)
                .title("1002")
                .snippet("Stanowisko ogniowe dzia?? kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Po????czone z obiektami 1001, 1003 i 1004 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val s1003 = LatLng(54.59527, 18.80871)
        map.addMarker(
            MarkerOptions()
                .position(s1003)
                .title("1003")
                .snippet("Stanowisko ogniowe dzia?? kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Po????czone z obiektami 1001, 1002 i 1004 podziemnymi poternami.")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val s1004 = LatLng(54.59492, 18.80929)
        map.addMarker(
            MarkerOptions()
                .position(s1004)
                .title("1004")
                .snippet("Stanowisko ogniowe dzia?? kalibru 100 mm z 27. BAS zbudowane w 1956 roku. Po????czone z obiektami 1001, 1002 i 1003 podziemnymi poternami.")
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
                .snippet("Schron-gara?? dla reflektora 27. BAS z 1957 roku")
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
                .snippet("Schron-gara?? agregatu dla reflektora 27. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val s1025 = LatLng(54.59623, 18.80892)
        map.addMarker(
            MarkerOptions()
                .position(s1025)
                .title("1025")
                .snippet("Przedwojenny schron agregatu, u??ywany tak??e w 27. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow))
        )

        val sK1 = LatLng(54.59790, 18.80795)
        map.addMarker(
            MarkerOptions()
                .position(sK1)
                .title("K1")
                .snippet("??elbetowa kopu??a dla km z Kompanijnego Rejonu Umocnionego 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val sK2 = LatLng(54.59793, 18.80968)
        map.addMarker(
            MarkerOptions()
                .position(sK2)
                .title("K2")
                .snippet("??elbetowa kopu??a dla km z Kompanijnego Rejonu Umocnionego 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val sK5 = LatLng(54.59499, 18.81346)
        map.addMarker(
            MarkerOptions()
                .position(sK5)
                .title("K5")
                .snippet("??elbetowa kopu??a dla km z Kompanijnego Rejonu Umocnionego 13. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red))
        )

        val pOMW = LatLng(54.59661, 18.80781)
        map.addMarker(
            MarkerOptions()
                .position(pOMW)
                .title("Punkt obserwacyjny MW nr 25")
                .snippet("Obiekt wykorzystywany przez Marynark?? Wojenn??")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsrec_green))
        )

        val pDziala = LatLng(54.59649, 18.81166)
        map.addMarker(
            MarkerOptions()
                .position(pDziala)
                .title("Podstawa dzia??a")
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
                .title("Kopiec Kaszub??w")
                .snippet("Symboliczny \"Pocz??tek Polski\"")
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
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )


        val bat4plot = LatLng(54.59460, 18.81243)
        map.addMarker(
            MarkerOptions()
                .position(bat4plot)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )


        val bat5plot = LatLng(54.59464, 18.81366)
        map.addMarker(
            MarkerOptions()
                .position(bat5plot)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val bat6plot = LatLng(54.59423, 18.81366)
        map.addMarker(
            MarkerOptions()
                .position(bat6plot)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val bat7plot = LatLng(54.59382, 18.81288)
        map.addMarker(
            MarkerOptions()
                .position(bat7plot)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val bat8plot = LatLng(54.59391, 18.81218)
        map.addMarker(
            MarkerOptions()
                .position(bat8plot)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red))
        )

        val lighthouse = LatLng(54.60005, 18.81286)
        map.addMarker(
            MarkerOptions()
                .position(lighthouse)
                .title("Latarnia morska")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsrec_green))
        )

        val plot21_magazyn = LatLng(54.603173, 18.819742)
        map.addMarker(
            MarkerOptions()
                .position(plot21_magazyn)
                .title("Zniszczony magazyn amunicji")
                .snippet("Zniszczony magazyn amunicji 21. Baterii Przeciwlotniczej")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsremove_yellow))
        )

        val plot21_dziala = LatLng(54.602893, 18.820450 )
        map.addMarker(
            MarkerOptions()
                .position(plot21_dziala)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow))
        )

        val plot21_dzialb = LatLng(54.603067, 18.820804)
        map.addMarker(
            MarkerOptions()
                .position(plot21_dzialb)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow))
        )

        val dzial = LatLng(54.602831, 18.823368)
        map.addMarker(
            MarkerOptions()
                .position(dzial)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val dzial5a = LatLng(54.604087, 18.824651)
        map.addMarker(
            MarkerOptions()
                .position(dzial5a)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val dzial5b = LatLng(54.603708, 18.824511)
        map.addMarker(
            MarkerOptions()
                .position(dzial5b)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val dzial5c = LatLng(54.603677, 18.823556)
        map.addMarker(
            MarkerOptions()
                .position(dzial5c)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val dzial5d = LatLng(54.604137, 18.823781)
        map.addMarker(
            MarkerOptions()
                .position(dzial5d)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val w6 = LatLng(54.604734, 18.822644)
        map.addMarker(
            MarkerOptions()
                .position(w6)
                .title("Dawna wie??a WOP")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_blue)
                ))
        val dzial6a = LatLng(54.605921, 18.823191)
        map.addMarker(
            MarkerOptions()
                .position(dzial6a)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))
        val dzial6b = LatLng(54.605753, 18.824071)
        map.addMarker(
            MarkerOptions()
                .position(dzial6b)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))
        val dzial6c = LatLng(54.605386, 18.824028)
        map.addMarker(
            MarkerOptions()
                .position(dzial6c)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))
        val dzial6d = LatLng(54.605237, 18.823309)
        map.addMarker(
            MarkerOptions()
                .position(dzial6d)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))
        val ws6 = LatLng(54.605579, 18.822472)
        map.addMarker(
            MarkerOptions()
                .position(ws6)
                .title("Ruiny wie??yczki strzelniczej")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsremove_blue)
                ))
        val pkt6 = LatLng(54.605567, 18.823438)
        map.addMarker(
            MarkerOptions()
                .position(pkt6)
                .title("Schron")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_blue)
                ))

        val bocianieGniazdo = LatLng(54.615754, 18.823970)
        map.addMarker(
            MarkerOptions()
                .position(bocianieGniazdo)
                .title("Bocianie  gniazdo")
                .snippet("Przedwojenny punkt widokowy w Helu")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsrec_green)
                ))


        val eBRU7a1 = LatLng(54.608153, 18.825417)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7a1)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7a2 = LatLng(54.607762, 18.825320)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7a2)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7a3 = LatLng(54.608588, 18.824623)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7a3)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7a4 = LatLng(54.608085, 18.823808)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7a4)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val eBRU7abc1 = LatLng(54.611816, 18.824060)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7abc1)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7abc2 = LatLng(54.612027, 18.825312)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7abc2)
                .title("Schron")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))
        val eBRU7abc3 = LatLng(54.611866, 18.825194)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7abc3)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7abc4 = LatLng(54.612711, 18.825408)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7abc4)
                .title("Schron")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))
        val eBRU7abc5 = LatLng(54.613077, 18.823262)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7abc5)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7abc6 = LatLng(54.613282, 18.823048)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7abc6)
                .title("Schron")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))
        val eBRU7abc7 = LatLng(54.613643, 18.823273)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7abc7)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7abc8 = LatLng(54.613848, 18.823230)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7abc8)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val eBRU7c1 = LatLng(54.614999, 18.821638)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c1)
                .title("Schron")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))
        val eBRU7c2 = LatLng(54.615110, 18.821382)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c2)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7c3 = LatLng(54.615487, 18.821319)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c3)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7c4 = LatLng(54.615634, 18.820260)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c4)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7c5 = LatLng(54.615590, 18.820055)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c5)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7c6 = LatLng(54.615753, 18.819877)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c6)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7c7 = LatLng(54.615923, 18.819762)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c7)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7c8 = LatLng(54.61535, 18.82009)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c8)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7c9 = LatLng(54.615080, 18.820106)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c9)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7c10 = LatLng(54.615088, 18.820681)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c10)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7c11 = LatLng(54.615088, 18.820681)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c11)
                .title("Schron")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))
        val eBRU7c12 = LatLng(54.614873, 18.820323)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7c12)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val eBRU7d1 = LatLng(54.6203568, 18.8232493)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7d1)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val eBRU7d2 = LatLng(54.6203382, 18.8218546)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7d2)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val eBRU7d3 = LatLng(54.6193691, 18.8214254)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7d3)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val eBRU7d4 = LatLng(54.6198536, 18.8204813)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7d4)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val eBRU7d5 = LatLng(54.6207171, 18.8209426)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7d5)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val eBRU7d6 = LatLng(54.6196673, 18.8218331)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7d6)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))


        val eBRU7e1 = LatLng(54.61023969175878, 18.816635532190773)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7e1)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7e2 = LatLng(54.610102995638705, 18.817901534742976)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7e2)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7e3 = LatLng(54.60943193529978, 18.819317740987813)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7e3)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7e4 = LatLng(54.60887270989933, 18.817150516279806)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7e4)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7e5 = LatLng(54.608288621833765, 18.81807319610599)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7e5)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val eBRU7e6 = LatLng(54.61118412513616, 18.815777225375722)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7e6)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7e7 = LatLng(54.60745597116532, 18.818244857468997)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7e7)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val eBRU7e8 = LatLng(54.60780394665063, 18.81830923048013)
        map.addMarker(
            MarkerOptions()
                .position(eBRU7e8)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))



        val mow9a = LatLng(54.62075521681579, 18.80028657516413)
        map.addMarker(
            MarkerOptions()
                .position(mow9a)
                .title("Stanowisko koszarowo-bojowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_blue)
                ))

        val mow9b = LatLng(54.62305353426166, 18.801820798644123)
        map.addMarker(
            MarkerOptions()
                .position(mow9b)
                .title("Stanowisko koszarowo-bojowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_blue)
                ))

        val mow9c = LatLng(54.62584705753908, 18.79972867562981)
        map.addMarker(
            MarkerOptions()
                .position(mow9c)
                .title("Stanowisko koszarowo-bojowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_blue)
                ))

        val mow9d = LatLng(54.62246654392306, 18.794353529027486)
        map.addMarker(
            MarkerOptions()
                .position(mow9d)
                .title("Wie??a kierowania ogniem")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))

        val mow9e1 = LatLng(54.6286426, 18.795408)
        map.addMarker(
            MarkerOptions()
                .position(mow9e1)
                .title("Schron amunicyjny")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_blue)
                ))

        val mow9e2 = LatLng(54.6291271, 18.7933373)
        map.addMarker(
            MarkerOptions()
                .position(mow9e2)
                .title("Schron amunicyjny")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_blue)
                ))

        val elektrowniaRUH = LatLng(54.62688381558725, 18.789661755818205)
        map.addMarker(
            MarkerOptions()
                .position(elektrowniaRUH)
                .title("Elektrownia R.U. Hel")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_yellow)
                ))


        val wiadukt = LatLng(54.627900491377225, 18.80411286833346)
        map.addMarker(
            MarkerOptions()
                .position(wiadukt)
                .title("Wiadukt kolejki w??skotorowej")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsrec_green)
                ))

        val rakietowyA = LatLng(54.6153435, 18.8118231)
        map.addMarker(
            MarkerOptions()
                .position(rakietowyA)
                .title("Stanowisko bojowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))
        val rakietowyB = LatLng(54.6149024, 18.8106751)
        map.addMarker(
            MarkerOptions()
                .position(rakietowyB)
                .title("Stanowisko bojowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))
        val rakietowyC = LatLng(54.61384, 18.8123059)
        map.addMarker(
            MarkerOptions()
                .position(rakietowyC)
                .title("Stanowisko bojowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))
        val rakietowyD = LatLng(54.6142562, 18.8133574)
        map.addMarker(
            MarkerOptions()
                .position(rakietowyD)
                .title("Stanowisko bojowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))
        val rakietowyE = LatLng(54.6149645, 18.8130891)
        map.addMarker(
            MarkerOptions()
                .position(rakietowyE)
                .title("Stanowisko bojowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))

        val dunska1a = LatLng(54.617872, 18.8234425)
        map.addMarker(
            MarkerOptions()
                .position(dunska1a)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow)
                ))

        val dunska1b = LatLng(54.6174247, 18.8236356)
        map.addMarker(
            MarkerOptions()
                .position(dunska1b)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow)
                ))

        val dunska2a = LatLng(54.6178471, 18.8231635)
        map.addMarker(
            MarkerOptions()
                .position(dunska2a)
                .title("Schron za??ogi")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow)
                ))

        val dunska2b = LatLng(54.6174122, 18.8233137)
        map.addMarker(
            MarkerOptions()
                .position(dunska2b)
                .title("Schron za??ogi")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow)
                ))


        val bplot60a = LatLng(54.622427191857696, 18.82059599839172)
        map.addMarker(
            MarkerOptions()
                .position(bplot60a)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val bplot60b = LatLng(54.622458249790576, 18.82119681316226)
        map.addMarker(
            MarkerOptions()
                .position(bplot60b)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val bplot60c = LatLng(54.62211039958803, 18.82142211870121)
        map.addMarker(
            MarkerOptions()
                .position(bplot60c)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val bplot60d = LatLng(54.62208555303117, 18.82066037140285)
        map.addMarker(
            MarkerOptions()
                .position(bplot60d)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))


        val bplot = LatLng(54.62294275047365, 18.820359964017584)
        map.addMarker(
            MarkerOptions()
                .position(bplot)
                .title("Dzia??obitnia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))

        val ppo3bas = LatLng(54.6266117, 18.8194245)
        map.addMarker(
            MarkerOptions()
                .position(ppo3bas)
                .title("Prawy punkt obserwacji dwubocznej")
                .snippet("Prawy punkt obserwacji dwubocznej 3 BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val goraSz = LatLng(54.62612885375458, 18.819979354485792)
        map.addMarker(
            MarkerOptions()
                .position(goraSz)
                .title("Nieczynna latarnia morska Szwedzka G??ra")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsrec_green)
                ))




        val grecka1 = LatLng(54.6393484, 18.8016415)
        map.addMarker(
            MarkerOptions()
                .position(grecka1)
                .title("Stanowisko 1")
                .snippet("Dzia??obitnia dla armat 105 mm baterii greckiej")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow)
                ))

        val grecka2 = LatLng(54.6396588, 18.8013196)
        map.addMarker(
            MarkerOptions()
                .position(grecka2)
                .title("Stanowisko 2")
                .snippet("Dzia??obitnia dla armat 105 mm baterii greckiej")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow)
                ))

        val grecka3 = LatLng(54.6393235, 18.801384)
        map.addMarker(
            MarkerOptions()
                .position(grecka3)
                .title("Schron za??ogi")
                .snippet("Schron amunicyjny baterii greckiej")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow)
                ))

        val grecka4 = LatLng(54.6394974, 18.8011479)
        map.addMarker(
            MarkerOptions()
                .position(grecka4)
                .title("Schron za??ogi")
                .snippet("Schron amunicyjny baterii greckiej")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow)
                ))

        val bplot115a = LatLng(54.6373079666546, 18.80443863847097)
        map.addMarker(
            MarkerOptions()
                .position(bplot115a)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))
        val bplot115b = LatLng(54.637422838852615, 18.804508375901033)
        map.addMarker(
            MarkerOptions()
                .position(bplot115b)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))
        val bplot115c = LatLng(54.6374321528004, 18.804739045862007)
        map.addMarker(
            MarkerOptions()
                .position(bplot115c)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))
        val bplot115d = LatLng(54.63727692005892, 18.80484096979825)
        map.addMarker(
            MarkerOptions()
                .position(bplot115d)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))
        val bplot115e = LatLng(54.63723655944906, 18.804701494938126)
        map.addMarker(
            MarkerOptions()
                .position(bplot115e)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))

        val bplot115f = LatLng(54.63713100074157, 18.804084586902963)
        map.addMarker(
            MarkerOptions()
                .position(bplot115f)
                .title("Schron za??ogi")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))

        val bplot115g = LatLng(54.63759359422289, 18.80374662858805)
        map.addMarker(
            MarkerOptions()
                .position(bplot115g)
                .title("Schron za??ogi")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))





        val bas3a = LatLng(54.6435827, 18.7925863)
        map.addMarker(
            MarkerOptions()
                .position(bas3a)
                .title("1108")
                .snippet("Zapasowy punkt kierowania ogniem 3. BAS zbudowany w 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val bas3b1 = LatLng(54.6394291, 18.7982619)
        map.addMarker(
            MarkerOptions()
                .position(bas3b1)
                .title("1101")
                .snippet("Stanowisko ogniowe dla dzia?? 152.4 mm 3. BAS z 1956 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val bas3b2 = LatLng(54.6401307, 18.7972963)
        map.addMarker(
            MarkerOptions()
                .position(bas3b2)
                .title("1102")
                .snippet("Stanowisko ogniowe dla dzia?? 152.4 mm 3. BAS z 1956 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))
        val bas3b3 = LatLng(54.6407702, 18.7963092)
        map.addMarker(
            MarkerOptions()
                .position(bas3b3)
                .title("1103")
                .snippet("Stanowisko ogniowe dla dzia?? 152.4 mm 3. BAS z 1956 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val bas3b4 = LatLng(54.6413849, 18.7952685)
        map.addMarker(
            MarkerOptions()
                .position(bas3b4)
                .title("1104")
                .snippet("Stanowisko ogniowe dla dzia?? 152.4 mm 3. BAS z 1956 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val bas3c = LatLng(54.63780005205114, 18.80027048240651)
        map.addMarker(
            MarkerOptions()
                .position(bas3c)
                .title("1105")
                .snippet("Centrala artyleryjska 3. BAS z 1956 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bas3d = LatLng(54.6380196, 18.8002896)
        map.addMarker(
            MarkerOptions()
                .position(bas3d)
                .title("1106")
                .snippet("Punkt kierowania ogniem z artyleryjsk?? stacj?? radiolokacyjn?? 3. BAS zbudowany" +
                        "w 1956 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bas3e = LatLng(54.6383239, 18.7952363)
        map.addMarker(
            MarkerOptions()
                .position(bas3e)
                .title("1109")
                .snippet("Elektrownia z hydroforni?? 3. BAS z 1958 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bas3f = LatLng(54.6383239, 18.7952363)
        map.addMarker(
            MarkerOptions()
                .position(bas3f)
                .title("1111")
                .snippet("Schron za??ogi dla 3. BAS z 1956 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bas3g = LatLng(54.6396712, 18.7935305)
        map.addMarker(
            MarkerOptions()
                .position(bas3g)
                .title("1113")
                .snippet("Magazyn amunicji 3. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bas3h1 = LatLng(54.6376533, 18.8035297)
        map.addMarker(
            MarkerOptions()
                .position(bas3h1)
                .title("1117")
                .snippet("Schron-gara?? dla reflektor??w do strzela?? nocnych 3. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bas3h2 = LatLng(54.6455196, 18.7881768)
        map.addMarker(
            MarkerOptions()
                .position(bas3h2)
                .title("1118")
                .snippet("Schron-gara?? dla reflektor??w do strzela?? nocnych 3. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bas3i = LatLng(54.6399382, 18.7978971)
        map.addMarker(
            MarkerOptions()
                .position(bas3i)
                .title("1121")
                .snippet("Schron obserwacyjny dow??dcy plutonu ogniowego 3. Baterii Artylerii Sta??ej")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bas3j1 = LatLng(54.6370696, 18.8041091)
        map.addMarker(
            MarkerOptions()
                .position(bas3j1)
                .title("1122")
                .snippet("Schron-gara?? dla reflektor??w do strzela?? nocnych 3. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bas3j2 = LatLng(54.6461715, 18.7877584)
        map.addMarker(
            MarkerOptions()
                .position(bas3j2)
                .title("1123")
                .snippet("Schron-gara?? dla reflektor??w do strzela?? nocnych 3. BAS z 1957 roku")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bat34a = LatLng(54.6437069, 18.7934232)
        map.addMarker(
            MarkerOptions()
                .position(bat34a)
                .title("34/1")
                .snippet("Fundament pojedynczej armaty 120 mm 34. Baterii")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow)
                ))

        val bat34b = LatLng(54.6443401, 18.7925863)
        map.addMarker(
            MarkerOptions()
                .position(bat34b)
                .title("34/2")
                .snippet("Fundament podw??jnej armaty 120 mm 34. Baterii")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow)
                ))

        val rtech = LatLng(54.6462025, 18.7878227)
        map.addMarker(
            MarkerOptions()
                .position(rtech)
                .title("RTECH")
                .snippet("Posterunek plutonu rozpoznania radiotechnicznego")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))


        val magTorp = LatLng(54.6364176, 18.7868357)
        map.addMarker(
            MarkerOptions()
                .position(magTorp)
                .title("Magazyny torped")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_yellow)
                ))


        val magMin = LatLng(54.6457928, 18.7765574)
        map.addMarker(
            MarkerOptions()
                .position(magMin)
                .title("Magazyny min morskich")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_yellow)
                ))


        val zbPP = LatLng(54.6520128, 18.7782311)
        map.addMarker(
            MarkerOptions()
                .position(zbPP)
                .title("Zbiorniki paliw p??ynnych")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_yellow)
                ))


        val batCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.594385, 18.812900))
                .radius(80.0)
                .strokePattern(patternDotted)
                .visible(true)
                .fillColor(0x2133ff33)


        )




        val bplotbor1 = LatLng(54.6535955, 18.772856)
        map.addMarker(
            MarkerOptions()
                .position(bplotbor1)
                .title("Schron dalmierza")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_blue)
                ))

        val bplotbor2 = LatLng(54.6532914, 18.7724698)
        map.addMarker(
            MarkerOptions()
                .position(bplotbor2)
                .title("Wie??a artylerii os??onowej")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))

        val bplotbor3 = LatLng(54.6516962, 18.7751412)
        map.addMarker(
            MarkerOptions()
                .position(bplotbor3)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))

        val bplotbor4 = LatLng(54.6516962, 18.7749696)
        map.addMarker(
            MarkerOptions()
                .position(bplotbor4)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))

        val bplotbor5 = LatLng(54.6515845, 18.7752378)
        map.addMarker(
            MarkerOptions()
                .position(bplotbor5)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))

        val bplotbor6 = LatLng(54.6514914, 18.7752914)
        map.addMarker(
            MarkerOptions()
                .position(bplotbor6)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))


        val bplot23a = LatLng(54.6550231, 18.7691438)
        map.addMarker(
            MarkerOptions()
                .position(bplot23a)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow)
                ))


        val bplot23b = LatLng(54.6550231, 18.7691438)
        map.addMarker(
            MarkerOptions()
                .position(bplot23b)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow)
                ))


        val bplot23c = LatLng(54.6547127, 18.7697232)
        map.addMarker(
            MarkerOptions()
                .position(bplot23c)
                .title("Magazyn amunicji")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_yellow)
                ))

        val wopk64 = LatLng(54.6705801, 18.7443066)
        map.addMarker(
            MarkerOptions()
                .position(bplot23c)
                .title("64. Dywizjon Rakietowy WOPK")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_grey)
                ))


        val bas3l1 = LatLng(54.6751215, 18.734953)
        map.addMarker(
            MarkerOptions()
                .position(bas3l1)
                .title("Schron za??ogi")
                .snippet("Schron za??ogi lewego punktu obserwacji dwubocznej 3. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))

        val bas3l2 = LatLng(54.6751213, 18.734951)
        map.addMarker(
            MarkerOptions()
                .position(bas3l2)
                .title("Schron za??ogi")
                .snippet("Schron za??ogi lewego punktu obserwacji dwubocznej 3. BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_red)
                ))


        val magAmun = LatLng(54.6379203, 18.7700558)
        map.addMarker(
            MarkerOptions()
                .position(magAmun)
                .title("Magazyny amunicji")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_yellow)
                ))

        val bplot22a = LatLng(54.630704518336415, 18.773113503360594)
        map.addMarker(
            MarkerOptions()
                .position(bplot22a)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow)
                ))

        val bplot22b = LatLng(54.6306920976843, 18.771986975665833)
        map.addMarker(
            MarkerOptions()
                .position(bplot22b)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_yellow)
                ))

        val bplot22c = LatLng(54.6312634437564, 18.7720191621714)
        map.addMarker(
            MarkerOptions()
                .position(bplot22c)
                .title("Fundament wie??y dowodzenia")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_yellow)
                ))


        val rcn9fw = LatLng(54.631754, 18.7778986)
        map.addMarker(
            MarkerOptions()
                .position(rcn9fw)
                .title("Radiowe centrum nadawcze 9.FOW")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_red)
                ))

        val bplot60e = LatLng(54.62987850117439, 18.772426857908574)
        map.addMarker(
            MarkerOptions()
                .position(bplot60e)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))

        val bplot60f = LatLng(54.6301579705202, 18.772523417425266)
        map.addMarker(
            MarkerOptions()
                .position(bplot60f)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))


        val bplot60g = LatLng(54.63005239343748, 18.7720406198418)
        map.addMarker(
            MarkerOptions()
                .position(bplot60g)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))


        val bplot60h = LatLng(54.629847448906304, 18.77215863702887)
        map.addMarker(
            MarkerOptions()
                .position(bplot60h)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_red)
                ))


        val wrakiOkr = LatLng(54.6137344, 18.7785959)
        map.addMarker(
            MarkerOptions()
                .position(wrakiOkr)
                .title("Wraki okr??t??w")
                .snippet("Wraki niszczycieli OORP Wicher II i Grom II")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsrec_green)
                ))

        val nukSchr = LatLng(54.6161698, 18.7852049)
        map.addMarker(
            MarkerOptions()
                .position(nukSchr)
                .title("Nieuko??czony schron przeciwlotniczy typu T-750")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_blue)
                ))

        val schrPlot = LatLng(54.6151757, 18.79529)
        map.addMarker(
            MarkerOptions()
                .position(schrPlot)
                .title("Schron przeciwlotniczy")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_red)
                ))

        val pwJast1 = LatLng(54.6948066, 18.6922073)
        map.addMarker(
            MarkerOptions()
                .position(pwJast1)
                .title("Podstawa wie??y dla dzia??ek")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_blue)
                ))
        val pwJast2 = LatLng(54.6935293, 18.6991167)
        map.addMarker(
            MarkerOptions()
                .position(pwJast2)
                .title("Podstawa wie??y dla dzia??ek")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_blue)
                ))

        val sskoJast = LatLng(54.6938889, 18.6943102)
        map.addMarker(
            MarkerOptions()
                .position(sskoJast)
                .title("Schron stanowiska kierowania ogniem")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_blue)
                ))

        val soJast1 = LatLng(54.6936905, 18.6974216)
        map.addMarker(
            MarkerOptions()
                .position(soJast1)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))

        val soJast2 = LatLng(54.6938021, 18.6982584)
        map.addMarker(
            MarkerOptions()
                .position(soJast2)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))

        val soJast3 = LatLng(54.6944842, 18.6967993)
        map.addMarker(
            MarkerOptions()
                .position(soJast3)
                .title("Stanowisko ogniowe")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_blue)
                ))


        val ooJast1 = LatLng(54.7163353, 18.6345935)
        map.addMarker(
            MarkerOptions()
                .position(ooJast1)
                .title("Schron Saba??a")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow)
                ))

        val ooJast2 = LatLng(54.7171967, 18.6368251)
        map.addMarker(
            MarkerOptions()
                .position(ooJast2)
                .title("Schron Saragossa")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow)
                ))

        val ooJast3 = LatLng(54.7175748, 18.6368895)
        map.addMarker(
            MarkerOptions()
                .position(ooJast3)
                .title("Schron S??p")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow)
                ))

        val ooJast4 = LatLng(54.7147178, 18.6334777)
        map.addMarker(
            MarkerOptions()
                .position(ooJast4)
                .title("Schron Sok????")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vsdot_full_yellow)
                ))


        val schrLekki1 = LatLng(54.617169955485615, 18.78315567140258)
        map.addMarker(
            MarkerOptions()
                .position(schrLekki1)
                .title("Schron lekki")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_red)
                ))

        val schrLekki2 = LatLng(54.61722586697146, 18.782833806346932)
        map.addMarker(
            MarkerOptions()
                .position(schrLekki2)
                .title("Schron lekki")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_red)
                ))

        val schrLekki3 = LatLng(54.617387388610155, 18.78279089100618)
        map.addMarker(
            MarkerOptions()
                .position(schrLekki3)
                .title("Schron lekki")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_red)
                ))

        val komp3Bas = LatLng(54.6407267, 18.7896466)
        map.addMarker(
            MarkerOptions()
                .position(komp3Bas)
                .title("Kompanijny Rejon Umocniony 3 BAS")
                .icon(BitmapDescriptorFactory.fromResource(com.example.wbunkry.R.drawable.vssquare_red)
                ))



        bat1Circle = map.addCircle(
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

        bat21Circle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.603105, 18.820182))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        batdywCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.603829, 18.824196))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        batPlotHelCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.605318, 18.823223))
                .radius(100.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )



        batRu2abcCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.612865, 18.823511))
                .radius(160.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )


        batRu2aCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.608656, 18.824258))
                .radius(240.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )
        batRu2bCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.60914436991315, 18.816481753893786))
                .radius(260.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        batRu2cCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.615590, 18.820413))
                .radius(80.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        batRu2dCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.62057902394468, 18.821482087079847))
                .radius(110.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        dywWLOPCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6146601, 18.8117909))
                .radius(200.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        mowCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.62290756392729, 18.799160047401173))
                .radius(330.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        mow9eCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6288662, 18.794378))
                .radius(90.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )





        elektrownia_RUHCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.62688381558725, 18.789661755818205))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        pktObsCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.61476338675549, 18.82229929820359))
                .radius(30.0)
                .fillColor(0x2133ff31)
                .visible(true)
                .strokePattern(patternDotted)
        )

        dunskaCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6176732, 18.8234854))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        batPlotCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.62294275047365, 18.820359964017584))
                .radius(40.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        szwedzkaCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.622377499115764, 18.821003694128873))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        prawyPObsCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.62661376996075, 18.819393632183544))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        batplot115Circle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6372536351077, 18.804669305105744))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        bas3Circle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6404536, 18.7971354))
                .radius(300.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )
        batart34Circle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6440669, 18.792994))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )
        pprrCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6462025, 18.7878227))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )
        batGreckaCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6395222, 18.8014054))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )


        magtorpCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6364176, 18.7868357))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        magminCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6457928, 18.7765574))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        zbpaliwCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6520128, 18.7782311))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        batplotborCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6526335, 18.7738752))
                .radius(200.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        batplot23Circle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6548307, 18.7692726))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        dywrak34Circle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6705801, 18.7443066))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        lpo3basCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6751213, 18.734951))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        magamunCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6379203, 18.7700558))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        batplot22Circle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6307418, 18.7723303))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )


        bat60plotCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6298661, 18.7723303))
                .radius(50.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )


        nukschronCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6161698, 18.7852049))
                .radius(60.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        oojastCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.71637249542112, 18.635677054082773))
                .radius(200.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )

        batplotjastCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.69433533595726, 18.69549036287925))
                .radius(270.0)
                .fillColor(0x2133ff33)
                .visible(true)
                .strokePattern(patternDotted)
        )




        wrakiCircle = map.addCircle(
            CircleOptions()
                .clickable(true)
                .center(LatLng(54.6137344, 18.7785959))
                .radius(60.0)
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
        val showBatPlotDetails = Intent(this, LongSinglePoiActivity::class.java).also {
            when (circle.id) {
                bat1Circle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATERIA_PRZECIWLOTNICZEJ.ordinal
                )
                bat27Circle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATERIA_STALEJ.ordinal
                )
                bat13Circle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATERIA_NADBRZEZNEJ.ordinal
                )
                bat21Circle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATERIA_21.ordinal
                )
                batdywCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATERIA_DYW60.ordinal
                )
                batPlotHelCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATERIA_PLOTHEL.ordinal
                )
                batRu2abcCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BAT_RU2.ordinal
                )

                batRu2aCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BAT_RU2.ordinal
                )
                batRu2bCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BAT_RU2.ordinal
                )


                batRu2cCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BAT_RU2.ordinal
                )

                batRu2dCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BAT_RU2.ordinal
                )

                dywWLOPCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.DYW_WLOP.ordinal
                )
                mowCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.MUZEUM_MOW.ordinal
                )

                mow9eCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.MUZEUM_MOW.ordinal
                )
                elektrownia_RUHCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.ELEKTROWNIA_RUH.ordinal
                )
                pktObsCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.PKT_PBS.ordinal
                )
                dunskaCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATERIA_DUNSKA.ordinal
                )
                batPlotCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BAT_PLOT.ordinal
                )
                szwedzkaCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATERIA_SZWEDZKA.ordinal
                )

                prawyPObsCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.PRPO_3BAS.ordinal
                )

                batplot115Circle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATPLOT_115.ordinal
                )
                bas3Circle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BAS_3.ordinal
                )
                batart34Circle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATART_34.ordinal
                )
                pprrCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.PPRR.ordinal
                )

                batGreckaCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BAT_GRECKA.ordinal
                )
                magtorpCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.MAG_TORP.ordinal
                )
                magminCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.MAG_MIN.ordinal
                )
                zbpaliwCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.ZB_PALIW.ordinal
                )
                batplotborCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATPLOT_BOR.ordinal
                )
                batplot23Circle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATPLOT_23.ordinal
                )
                dywrak34Circle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.DYWRAK_34.ordinal
                )
                lpo3basCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.LPO_3BAS.ordinal
                )
                magamunCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.MAG_AMUN.ordinal
                )
                batplot22Circle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATPLOT_22.ordinal
                )

                bat60plotCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BAT_PLOT.ordinal )

                nukschronCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.NUK_SCHRON.ordinal
                )
                oojastCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.OO_JAST.ordinal
                )
                batplotjastCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.BATPLOT_JAST.ordinal
                )
                wrakiCircle.id -> it.putExtra(
                    "position",
                    LongPathDB.PoiIndex.WRAKI.ordinal
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
                    } else {
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
            updateLocationUI()
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
                    updateLocationUI()
                }
            }
        }
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
        private val TAG = LongMapActivity::class.java.simpleName
        private const val DEFAULT_ZOOM = 14
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1

        // Keys for storing activity state.
        private const val KEY_CAMERA_POSITION = "camera_position"
        private const val KEY_LOCATION = "location"

        // Used for selecting the current place.
        private const val M_MAX_ENTRIES = 5
    }
}

