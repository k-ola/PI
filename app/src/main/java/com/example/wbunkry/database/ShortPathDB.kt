package com.example.wbunkry.database

import android.graphics.Point
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

object ShortPathDB {

    val poiList: ArrayList<Pois> = arrayListOf<Pois>(
        Pois("31LASK.jpg","Bateria Artylerii Nadbrzeżnej nr 31 im. H. Laskowskiego","54.59600047857394","18.81244502857111",
            "Jedyna polska przedwojenna, nowoczesna bateria artylerii nadbrzeżnej wybudowana w 1935 r., uzbrojona w cztery armaty Bofors kalibru 152 mm. W baterii zbudowano cztery działobitnie, magazyn amunicyjny i drewniane wieże kierowania ogniem \n" +
                "W trakcie kampanii wrześniowej bateria pod dowództwem kpt. Zbigniewa Przybyszewskiego bohatersko wspierała obrońców oraz prowadziła skuteczne pojedynki ogniowe z okrętami niemieckimi w tym z pancernikiem „Schleswig- Holstein”. Po walkach została wcielona w skład niemieckiego systemu obrony wybrzeża pod nazwą „Schleisen”. Po zakończeniu II wojny światowej w 1948 r. na jej bazie utworzono 13 Baterie Artylerii Stałej adaptując trzy stanowiska ogniowe i schrony pomocnicze. Zniszczone wybuchem w 1946 r, skrajne prawe stanowisko ogniowe zostało zastąpione wybudowanym w 1948 r. według tego samego wzoru schronem artyleryjskim. Pozostałe przedwojenne działobitnie zostały przebudowane pod nowe armaty radzieckie. Bateria została rozformowana w 1977 r., a na jej miejscu utworzono 7 Dywizjon Artylerii Przeciwlotniczej MW. Obecnie na teren cypla można dostać się przez dawną bramę jednostki wojskowej. Na udostępnionym do zwiedzania schronie obecnego stanowiska ogniowego nr 3, przy brukowanej drodze można podziwiać armatę morską B-13 kal. 130 mm z doskonale zachowanym parasolem maskującym. Jedyne dwa ocalałe działa kał, 152,4 mm z Baterii Laskowskiego można obejrzeć w Muzeum Marynarki Wojennej w Gdyni oraz w Muzeum Wojska Polskiego w Warszawie.\n", MarkerOptions().title("Bateria nr 31 im. H. Laskowskiego").position(LatLng(54.59600047857394,18.81244502857111)
        )),
        Pois("27BAS_s.jpg","27. Bateria Artylerii Stałej","54.595660407136165","18.808104214431733",
            "Powojenna polska bateria nadbrzeżna wybudowana w latach 1955-1958, uzbrojona z radzieckie działa kal. 100 mm. Miała bronić portu helskiego przed bezpośrednim atakiem z wody i powietrza oraz atakować kutry torpedowe przedzierające się w stronę portu w Gdyni. Stanowiska ogniowe były połączone podziemnym korytarzem o łącznej długości 250 m. W baterii wybudowano lekkie schrony centrali artyleryjskiej i garaże stanowiska reflek¬tora oraz ciekawą wieżę punktu kierowania ogniem. Podobnie jak w 13. BAS wykorzystano w niej obiekty po przedwojennej Baterii Laskowskiego. Bateria została rozformowana wraz z sąsiednią 13. BAS w 1977 roku. Obecnie szczególnie warte zobaczenia są stanowiska ogniowe z zachowaną radziecką armatą kalibru 100 mm typ B-34U. Roztacza się także stąd widok na Zatokę Gdańską oraz wieżę kierowania ogniem, zwaną „kurzą stopką”.", MarkerOptions().title("27. Bateria Artylerii Stałej").position(LatLng(54.595660407136165,18.808104214431733)
        )),
        Pois("7dyw.jpg","Bateria 7. Dywizjonu Artylerii Przeciwlotniczej Marynarki Wojennej","54.59386051124881", "18.812303111696682",
            "Zbudowana w 1974 r. W skład baterii wchodziło 6 stanowisk ogniowych z betonowych elementów prefabrykowanych dla armat przeciwlotniczych typu S-60 kalibru 57 mm, ukrycia dla dalmierza, dla radaru kierowania ogniem, dla ciągników, oraz schrony dla załogi. Dywizjon rozformowano w 1990 r.", MarkerOptions().title("Bateria 7. Dywizjonu Artylerii Przeciwlotniczej Marynarki Wojennej\n\n\n\n\n\n\n   ").position(LatLng(54.59386051124881,18.812303111696682))
        )

    )

    enum class PoiIndex {
        BATERIA_NADBRZEZNEJ,
        BATERIA_STALEJ,
        BATERIA_PRZECIWLOTNICZEJ

    }

}
data class Pois(
    val img: String,
    val name: String,
    val lat: String,
    val lng: String,
    val desc: String,
    val marker: MarkerOptions
)