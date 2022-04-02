package com.example.wbunkry.database

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

object MediumPathDB {

    val poiListMedium: ArrayList<MediumPois> = arrayListOf<MediumPois>(
        MediumPois("31LASK.jpg","Bateria nr 31 im. H. Laskowskiego","54.59600047857394","18.81244502857111","Jedyna polska przedwojenna, nowoczesna bateria artylerii nadbrzeżnej wybudowana w 1935 r., uzbrojona w cztery armaty Bofors kalibru 152 mm. W baterii zbudowano cztery działobitnie, magazyn amunicyjny i drewniane wieże kierowania ogniem \n" +
                "W trakcie kampanii wrześniowej bateria pod dowództwem kpt. Zbigniewa Przybyszewskiego bohatersko wspierała obrońców oraz prowadziła skuteczne pojedynki ogniowe z okrętami niemieckimi w tym z pancernikiem „Schleswig- Holstein”. Po walkach została wcielona w skład niemieckiego systemu obrony wybrzeża pod nazwą „Schleisen”. Po zakończeniu II wojny światowej w 1948 r. na jej bazie utworzono 13 Baterie Artylerii Stałej adaptując trzy stanowiska ogniowe i schrony pomocnicze. Zniszczone wybuchem w 1946 r, skrajne prawe stanowisko ogniowe zostało zastąpione wybudowanym w 1948 r. według tego samego wzoru schronem artyleryjskim. Pozostałe przedwojenne działobitnie zostały przebudowane pod nowe armaty radzieckie. Bateria została rozformowana w 1977 r., a na jej miejscu utworzono 7 Dywizjon Artylerii Przeciwlotniczej MW. Obecnie na teren cypla można dostać się przez dawną bramę jednostki wojskowej. Na udostępnionym do zwiedzania schronie obecnego stanowiska ogniowego nr 3, przy brukowanej drodze można podziwiać armatę morską B-13 kal. 130 mm z doskonale zachowanym parasolem maskującym. Jedyne dwa ocalałe działa kał, 152,4 mm z Baterii Laskowskiego można obejrzeć w Muzeum Marynarki Wojennej w Gdyni oraz w Muzeum Wojska Polskiego w Warszawie.\n", MarkerOptions().title("Bateria nr 31 im. H. Laskowskiego").position(
            LatLng(54.59600047857394,18.81244502857111)
        )),
        MediumPois("27BAS_s.jpg","27. Bateria Artylerii Stałej","54.595660407136165","18.808104214431733","Powojenna polska bateria nadbrzeżna wybudowana w latach 1955-1958, uzbrojona z radzieckie działa kal. 100 mm. Miała bronić portu helskiego przed bezpośrednim atakiem z wody i powietrza oraz atakować kutry torpedowe przedzierające się w stronę portu w Gdyni. Stanowiska ogniowe były połączone podziemnym korytarzem o łącznej długości 250 m. W baterii wybudowano lekkie schrony centrali artyleryjskiej i garaże stanowiska reflek¬tora oraz ciekawą wieżę punktu kierowania ogniem. Podobnie jak w 13. BAS wykorzystano w niej obiekty po przedwojennej Baterii Laskowskiego. Bateria została rozformowana wraz z sąsiednią 13. BAS w 1977 roku. Obecnie szczególnie warte zobaczenia są stanowiska ogniowe z zachowaną radziecką armatą kalibru 100 mm typ B-34U. Roztacza się także stąd widok na Zatokę Gdańską oraz wieżę kierowania ogniem, zwaną „kurzą stopką”.", MarkerOptions().title("27. Bateria Artylerii Stałej").position(
            LatLng(54.595660407136165,18.808104214431733)
        )),
        MediumPois("7dyw.jpg","Bateria 7. Dywizjonu Artylerii Przeciwlotniczej Marynarki Wojennej","54.59386051124881", "18.812303111696682","Zbudowana w 1974 r. W skład baterii wchodziło 6 stanowisk ogniowych z betonowych elementów prefabrykowanych dla armat przeciwlotniczych typu S 60 kalibru 57 mm, ukrycia dla dalmierza, radaru kierowania ogniom, ciągników oraz schrony dla załogi. Dywizjon rozformowano w 1990 r.", MarkerOptions().title("Bateria 7. Dywizjonu Artylerii Przeciwlotniczej Marynarki Wojennej").position(
            LatLng(54.59386051124881,18.812303111696682)
        )),

        MediumPois("tlo.jpg","21. Bateria przeciwlotnicza","54.60298761531626","18.820255798203224","Tzw. „bateria cyplowa\". Wraz z bliźniaczymi bateriami 22 i 23 wchodziła w skład 2 Morskiego Dywizjonu Artylerii Przeciwlotniczej. Uzbrojona była w dwie armaty kalibru 75 mm firmy Schneider ustawiono na stropach schronów amunicyjnych zbudowanych w latach 1933-1935. Na zapleczu znajdował się zniszczony obecnie schron amunicyjny. Podstawowym jej zadaniom w trakcie kampa¬nii wrześniowej była obrona przeciwlotnicza baterii im H. Laskowskiego oraz ewentualna obrona przeciwdesantowa. Podczas okupacji Kriegsmarine zaadaptowała schrony na potrzeby własnej baterii przeciwlotniczej.",
            MarkerOptions().title("21. Bateria przeciwlotnicza").position(
                LatLng(54.60298761531626,18.820255798203224)
            )),

        MediumPois("tlo.jpg","Bateria 2. Dywizjonu 60. Samodzielnego Pułku Artylerii Przeciwlotniczej Marynarki Wojennej","54.622589656377905","18.819356673532823","Zbudowana z betonowych elementów prefabrykowanych pod koniec 1957 r. W skład baterii wchodziły 4 stanowiska ogniowe dla armat plot kal. 85 mm. ze schronami załogi. Obecnie zachowały się resztki stanowisk ogniowych i schronów po prawej stronie drogi na plażę.",
            MarkerOptions().title("Bateria 2. Dywizjonu 60. Samodzielnego Pułku Artylerii Przeciwlotniczej Marynarki Wojennej").position(
                LatLng(54.603829,18.824196)
            )),

        MediumPois("tlo.jpg","Bateria Przeciwlotnicza \"Hel\"","54.605384909741815","18.824370820154886","Niemiecka tzw. zaporowa bateria przeciwlotnicza (Sperr- batterie) zbudowana ok. 1940 r. Uzbrojona w cztery działa S.K.C/32 kalibru 105 mm ustawione na betonowych funda¬mentach. Zadaniem baterii była ochrona podejść do Zatoki Gdańskiej, a rozmieszczenie stanowisk ogniowych umożliwiało zarówno walkę z samolotami jak i mniejszymi okrętami przeciwnika. W pobliżu wzniesiono dwukondy¬gnacyjny schron kierowania ogniem wyposażony w dalmierz zamontowany na stropie pod pancerną kopułą (obecnie zdemontowany). Po wojnie na schronie wzniesio¬no wieżę Wojsk Ochrony Pogranicza, służącą obecnie jako punkt obserwacji przeciwpożarowej miejscowego nadle¬śnictwa. Niedaleko schronu znajdują się ruiny schronu najprawdopodobniej elektrowni bateryjnej. ",
            MarkerOptions().title("Bateria Przeciwlotnicza Hel").position(
                LatLng(54.605384909741815,18.824370820154886)
            )),
        MediumPois("tlo.jpg","Batalionowy Rejon Umocniony nr 2 Hel","54.61533139583248","18.820449455874428","System fortyfikacji polowych mających zapewnić obronę przeciwdesantową od strony morza. Budowę helskiego BRU nr 2 rozpoczęto w maju 1953 r. siłami gdańskiej 5. Brygady Przeciwdesantowej i przekazano do eksploatacji w czerwcu 1956 r. Był to pierwszy tego typu system umocnień i posłużył jako wzór dla ośmiu podobnych zbudowanych w innych rejonach naszego wybrzeża. BRU nr 2 zajmował południowo-wschodnią część półwyspu, rozciągał się wzdłuż wybrzeża na długości niemal 4 km i wchodził w głąb lądu na szerokość do 1,5 km. BRU tworzyły lekkie fortyfikacje zbudowane z prefabrykatów żelbetowych: główne i zapasowe stanowiska dowodzenia dowód¬ców batalionu, kompanii i plutonów, punkty obserwacyj¬ne, batalionowe punkty medyczne, punkty amunicyjne, schrony dla żołnierzy, stanowiska ogniowe dla czołgów, dział samobieżnych, dział polowych i przeciwlotniczych, moździerzy, stanowiska ogniowe dla ręcznych i ciężkich karabinów maszynowych a także przeszkody przeciwpan¬cerne i przeciwpiechotne. Największą częścią systemu były rowy strzeleckie i transzeje obłożone betonowymi płytami. Na helskim BRU tworzą one trzy linie obrony, a ich łączna długość wynosiła ok. 14,5 km.",
            MarkerOptions().title("Batalionowy Rejon Umocniony nr 2 Hel").position(
                LatLng(54.60978,18.82480)
            )),
        MediumPois("tlo.jpg","22. Dywizjon Rakietowy WLOP","54.61441721369485","18.814303798203582","Formowanie jednostki rozpoczęto w 1963 r. Dywizjon uzbrojono w 6 wyrzutni kierowanych rakiet przeciwlotni¬czych systemu S-75M „Wołchow” (mogących także razić cele nawodne), które rozmieszczono w ziemnych obwało¬waniach wokół obsypanego ziemią schronu mieszczącego stanowisko dowodzenia, garaże na pojazdy z wyposaże¬niem, gazoszczelny schron przeciwlotniczy oraz pomiesz¬czenia socjalno-bytowe obsługi. Na schronie znajduje się stanowisko radaru naprowadzania rakiet. Dywizjon chroniony był przed bezpośrednim atakiem lotniczym przez baterię małokalibrowej artylerii przeciwlotniczej, której stanowiska rozrzucone są po terenie dywizjonu. W północno-wschodniej części, tuż przy pozostałościach ogrodzenia, można zobaczyć zachowane stanowiska postojowe dla radarów i pojazdów 214. kompanii radiotechnicznej przeniesionej do Helu w 1978 r. z Lisewa. Nieco dalej, już za ogrodzeniem, znajdują się dwa zacho¬wane z 6 stanowisk ogniowych armat S-60 kal. 57 mm dla 76. baterii artylerii przeciwlotniczej, wykonanych z betonowych prefabrykatów. 22. dywizjon, który wchodził w skład 4. Brygady Rakietowej OP, rozformowano we wrześniu 2001 r. W tymże roku rozformowano również 214. kompanię radiotechniczną. W 2010 roku obiekty dywizjonu rozebrano, zachowując jedynie ziemne obwałowania stanowisk rakiet.",
            MarkerOptions().title("22. Dywizjon Rakietowy WLOP").position(
                LatLng(54.61441721369485,18.814303798203582)
            )),
        MediumPois("tlo.jpg","Najcięższa Bateria Artylerii Nadbrzeżnej Schleswig-Holstein","54.623071049816154","18.801718467398256","Budowę baterii, będącej największym obiektem tego typu na Bałtyku, rozpoczęto w końcu 1940 r. W jej skład weszły trzy żelbetowe schrony koszarowo-bojowe na których ustawiono armaty kal. 406 mm, dwa schrony amunicyjne oraz 25 metrowa, 9-kondygnacyjna wieża kierowania ogniem z umieszczonym na stropie dalmie¬rzem. Bateria „Schleswig-Holstein” weszła do służby jako 2 Schwere Artillerie Batterie (2 ciężka bateria artylerii) i wchodziła w skład stacjonującego w Helu Marine Artillerie Abteilung 119 (119 Oddziału Artylerii Marynarki). Pomiędzy październikiem 1941 r., a majem 1942 r. armaty oraz pozostałe wyposażenie zdemontowano i przetrans¬portowano do Francji w rejon Calais gdziebateria, pod nazwa zmienioną na „Lindemann”, funkcjonowała jako element Wału Atlantyckiego. Po wojnie w wieży kierowania ogniem funkcjonował przez pewien czas punkt obserwacji dwubocznej 13. BAS, a północny schron koszarowo-bojowy został zaadaptowany przez wojsko na magazyn płodów rolnych, w którym wykonano prowizoryczny strop nad stanowiskiem armaty. Obecnie środkowy schron koszarowo-bojowy (stanowisko „Bruno”) oraz wieża kierowania ogniem zostały zaadaptowane na potrzeby Muzeum Obrony Wybrzeża i są udostępnione do zwiedzania. ",
            MarkerOptions().title("Najcięższa Bateria Artylerii Nadbrzeżnej Schleswig-Holstein").position(
                LatLng(54.623071049816154,18.801718467398256)
            )),
        MediumPois("tlo.jpg","Elektrownia Rejonu Umocnionego Hel","54.62688381558725","18.789661755818205","Budowę rozpoczęto w 1935 r., a oddano do użytku wiosną 1939 r. Miała zapewnić dostawę elektryczności dla potrzeb wojska jak i dla odbiorców cywilnych. Prąd wytwarzany był przy pomocy dwóch agregatów o mocy 400 KM oraz jednego o mocy 100 KM. Elektrownia również zapewniała energię.",
            MarkerOptions().title("Elektrownia Rejonu Umocnionego Hel").position(
                LatLng(54.62688381558725,18.789661755818205)
            )),
        MediumPois("tlo.jpg","Punkt obserwacyjny","54.61476338675549","18.82229929820359","Punkt obserwacyjny MW czynny od 1920 roku został w 1928 zmodernizowany przez dobudowanie wieży drew¬nianej i wartowni. Rozbudowany przez Niemców po zajęciu Półwyspu. W pobliżu na wysokiej (15 m n. p. m.) wydmie nazywanej Bocianie Gniazdo lub potocznie „bocianica” przed wojną funkcjonował punkt widokowy stanowiący atrakcję dla letników. Wieża konstrukcji drewnianej stała jeszcze w latach 50-tych XX w. Obecnie zachowały się jedynie betonowe fundamenty składające się z 9 kwadratowych bloków. Po wojnie w bezpośrednim sąsiedztwie wieży zbudowano fragment Batalionowego Rejonu Umocnionego.",
            MarkerOptions().title("Punkt obserwacyjny").position(
                LatLng(54.61476338675549,18.82229929820359)
            )),
        MediumPois("tlo.jpg","Bateria Artylerii nr 33 \"Duńska\"","54.617684076054765","18.82369119820373","Oddana do użytku w 1932 r. uzbrojona była w dwie armaty kal. 105 mm firmy Schneider, które zaadaptowano na potrzeby baterii nadbrzeżnej poprzez ustawienie na betonowych działobitniach. W pobliżu stanowisk ognio¬wych wybudowano dwa schrony dla załogi. W trakcie kampanii wrześniowej prowadziła ostrzał lekkich sił nawodnych Kriegsmarine. Wraz z baterią „grecką” były pierwszymi stałymi elementami obrony wybrzeża na Półwyspie Helskim. Podczas okupacji Kriegsmarine zaadaptowała działobitnie i schrony na potrzeby własnej baterii nadbrzeżnej. Obiekty baterii zostały odnowione przez Muzeum Obrony Wybrzeża w 2011 roku.",
            MarkerOptions().title("Bateria Artylerii nr 33 \"Duńska\"").position(
                LatLng(54.617684076054765,18.82369119820373)
            )),
        MediumPois("tlo.jpg","Bateria 2. Dywizjonu 60. Samodzielnego Pułku Artylerii Przeciwlotniczej Marynarki Wojennej","54.62240418981283","18.82100400691485","Zbudowana z betonowych elementów prefabrykowanych pod koniec 1957 r. W skład baterii wchodziły 4 stanowiska ogniowe dla armat plot kalibru 85 mm, ze schronami załogi, obecnie zachowane szczątkowo.",
            MarkerOptions().title("Bateria 2. Dywizjonu 60. Samodzielnego Pułku Artylerii Przeciwlotniczej Marynarki Wojennej").position(
                LatLng(54.62240418981283,18.82100400691485)
            )),
        MediumPois("tlo.jpg","Bateria Przeciwlotnicza \"Szwedzka górka\"","54.62262470075269","18.820486340596105","Niemiecka tzw. zaporowa bateria przeciwlotnicza zbudowana ok. 1943 r. Zadaniem baterii była ochrona podejść do Zatoki Gdańskiej, a rozmieszczenie stanowisk ogniowych umożliwiało zarówno walkę z samolotami jak i mniejszymi okrętami przeciwnika. Obecnie zachował się jedynie fundament po jednym z czterech dział kalibru 105 mm, cokół dalmierza, betonowy fundament pod działko kalibru 20 mm. Po wojnie ustawiono tutaj baterię przeciwlotniczą.",
            MarkerOptions().title("Bateria Przeciwlotnicza \"Szwedzka górka\"").position(
                LatLng(54.62262470075269,18.820486340596105)
            )),
        MediumPois("tlo.jpg","Prawy Punkt Obserwacji Dwubocznej 3. Baterii Artylerii Stałej","54.62661376996075","18.819393632183544","Stanowisko zbudowane około 1957 r., służyło do obserwacji i awaryjnego kierowania ogniem 3 BAS. Punkt wzniesiono tuż przy autonomicznej latarni morskiej „Szwedzka Górka\". Podobny obiekt zbudowano na lewym skrzydle baterii, niedaleko Juraty.",
            MarkerOptions().title("Prawy Punkt Obserwacji Dwubocznej 3. Baterii Artylerii Stałej").position(
                LatLng(54.62661376996075,18.819393632183544)
            ))

        )
    enum class PoiIndex {
        BATERIA_NADBRZEZNEJ,
        BATERIA_STALEJ,
        BATERIA_PRZECIWLOTNICZEJ,
        BATERIA_21,
        BATERIA_DYW60,
        BATERIA_PLOTHEL,
        BAT_RU2,
        DYW_WLOP,
        MUZEUM_MOW,
        ELEKTROWNIA_RUH,
        PKT_PBS,
        BATERIA_DUNSKA,
        BAT_PLOT,
        BATERIA_SZWEDZKA,
        PRPO_3BAS


    }

}

data class MediumPois(
    val img: String,
    val name: String,
    val lat: String,
    val lng: String,
    val desc: String,
    val marker: MarkerOptions
)

