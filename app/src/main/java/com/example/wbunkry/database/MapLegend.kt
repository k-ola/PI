package com.example.wbunkry.database

object MapLegend {
    val pinDesc: ArrayList<Pin> = arrayListOf<Pin> (
        Pin("sdot_full_yellow.png", "FORTYFIKACJE POLSKIE Z LAT 1920-1939"),
        Pin("vsdot_full_yellow.png", "Schrony"),
        Pin("vsdot_yellow.png", "Działobitnie"),
        Pin("vsremove_yellow.png", "Obiekty w ruinie"),
        Pin("sdot_full_blue.png", "FORTYFIKACJE NIEMIECKIE Z LAT 1920-1939"),
        Pin("vsdot_full_blue.png", "Schrony"),
        Pin("vsdot_blue.png", "Działobitnie"),
        Pin("vsremove_blue.png", "Obiekty w ruinie"),
        Pin("sdot_full_red.png", "FORTYFIKACJE POLSKIE Z LAT 1946-1977"),
        Pin("vsdot_full_red.png", "Schrony"),
        Pin("vsdot_red.png", "Działobitnie"),
        Pin("vsremove_red.png", "Obiekty w ruinie"),
        Pin("srec_green", "INNE OBIEKTY"),
        Pin("", "ZESPOŁY OBIEKTÓW"),
        Pin("", ""),
        Pin("", ""),
        Pin("", ""),
        Pin("", ""),
        Pin("", "")
            )



}

data class Pin (
    val icon: String,
    val description: String,
        )