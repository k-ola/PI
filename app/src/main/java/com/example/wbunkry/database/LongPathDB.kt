package com.example.wbunkry.database

import android.graphics.Point
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

object LongPathDB {

    val poiListLong: ArrayList<LongPois> = arrayListOf<LongPois>(
        LongPois("31LASK.jpg","Bateria nr 31 im. H. Laskowskiego","54.59600047857394","18.81244502857111","Jedyna polska przedwojenna, nowoczesna bateria artylerii nadbrzeżnej wybudowana w 1935 r., uzbrojona w cztery armaty Bofors kalibru 152 mm. W baterii zbudowano cztery działobitnie, magazyn amunicyjny i drewniane wieże kierowania ogniem \n" +
                "W trakcie kampanii wrześniowej bateria pod dowództwem kpt. Zbigniewa Przybyszewskiego bohatersko wspierała obrońców oraz prowadziła skuteczne pojedynki ogniowe z okrętami niemieckimi w tym z pancernikiem „Schleswig- Holstein”. Po walkach została wcielona w skład niemieckiego systemu obrony wybrzeża pod nazwą „Schleisen”. Po zakończeniu II wojny światowej, w 1948 r., na jej bazie utworzono 13 Baterie Artylerii Stałej adaptując trzy stanowiska ogniowe i schrony pomocnicze. Zniszczone wybuchem w 1946 r. skrajne prawe stanowisko ogniowe zostało zastąpione wybudowanym w 1948 r. według tego samego wzoru schronem artyleryjskim. Pozostałe przedwojenne działobitnie zostały przebudowane pod nowe armaty radzieckie. Bateria została rozformowana w 1977 r., a na jej miejscu utworzono 7 Dywizjon Artylerii Przeciwlotniczej MW. Obecnie na teren cypla można dostać się przez dawną bramę jednostki wojskowej. Na udostępnionym do zwiedzania schronie obecnego stanowiska ogniowego nr 3, przy brukowanej drodze można podziwiać armatę morską B-13 kal. 130 mm z doskonale zachowanym parasolem maskującym. Jedyne dwa ocalałe działa kal. 152,4 mm z Baterii Laskowskiego można obejrzeć w Muzeum Marynarki Wojennej w Gdyni oraz w Muzeum Wojska Polskiego w Warszawie.\n"
            ),
        LongPois("27BAS_s.jpg","27. Bateria Artylerii Stałej","54.595660407136165","18.808104214431733","Powojenna polska bateria nadbrzeżna wybudowana w latach 1955-1958, uzbrojona w radzieckie działa kal. 100 mm. Miała bronić portu helskiego przed bezpośrednim atakiem z wody i powietrza oraz atakować kutry torpedowe przedzierające się w stronę portu w Gdyni. Stanowiska ogniowe były połączone podziemnym korytarzem o łącznej długości 250 m. W baterii wybudowano lekkie schrony centrali artyleryjskiej i garaże stanowiska reflek¬tora oraz ciekawą wieżę punktu kierowania ogniem. Podobnie jak w 13. BAS wykorzystano w niej obiekty po przedwojennej Baterii Laskowskiego. Bateria została rozformowana wraz z sąsiednią 13. BAS w 1977 roku. Obecnie szczególnie warte zobaczenia są stanowiska ogniowe z zachowaną radziecką armatą kalibru 100 mm typ B-34U. Roztacza się także stąd widok na Zatokę Gdańską oraz wieżę kierowania ogniem, zwaną „kurzą stopką”."
            ),
        LongPois("BAT7DYWAPLOTMW.jpg","Bateria 7. Dywizjonu Artylerii Przeciwlotniczej Marynarki Wojennej","54.59386051124881", "18.812303111696682","Zbudowana w 1974 r. W skład baterii wchodziło 6 stanowisk ogniowych z betonowych elementów prefabrykowanych dla armat przeciwlotniczych typu S-60 kalibru 57 mm, ukrycia dla dalmierza, radaru kierowania ogniem, ciągników oraz schrony dla załogi. Dywizjon rozformowano w 1990 r."
            ),
        LongPois("21BATPLOT.jpg","21. Bateria przeciwlotnicza","54.60298761531626","18.820255798203224","Tzw. „bateria cyplowa\". Wraz z bliźniaczymi bateriami 22 i 23 wchodziła w skład 2 Morskiego Dywizjonu Artylerii Przeciwlotniczej. Uzbrojona była w dwie armaty kalibru 75 mm firmy Schneider, które ustawiono na stropach schronów amunicyjnych zbudowanych w latach 1933-1935. Na zapleczu znajdował się zniszczony obecnie schron amunicyjny. Podstawowym jej zadaniem w trakcie kampanii wrześniowej była obrona przeciwlotnicza baterii im. H. Laskowskiego oraz ewentualna obrona przeciwdesantowa. Podczas okupacji Kriegsmarine zaadaptowała schrony na potrzeby własnej baterii przeciwlotniczej."
            ),

        LongPois("BAT2DYW60PLOT.jpg","Bateria 2. Dywizjonu 60. Samodzielnego Pułku Artylerii Przeciwlotniczej Marynarki Wojennej","54.622589656377905","18.819356673532823","Zbudowana z betonowych elementów prefabrykowanych pod koniec 1957 r. W skład baterii wchodziły 4 stanowiska ogniowe dla armat plot kal. 85 mm. ze schronami załogi. Obecnie zachowały się resztki stanowisk ogniowych i schronów po prawej stronie drogi na plażę."
            ),

        LongPois("BATPLOTHEL.jpg","Bateria Przeciwlotnicza Hel","54.605384909741815","18.824370820154886","Niemiecka tzw. zaporowa bateria przeciwlotnicza (Sperrbatterie) zbudowana ok. 1940 r. Uzbrojona w cztery działa S.K.C/32 kalibru 105 mm ustawione na betonowych fundamentach. Zadaniem baterii była ochrona podejść do Zatoki Gdańskiej, a rozmieszczenie stanowisk ogniowych umożliwiało zarówno walkę z samolotami jak i mniejszymi okrętami przeciwnika. W pobliżu wzniesiono dwukondygnacyjny schron kierowania ogniem wyposażony w dalmierz zamontowany na stropie pod pancerną kopułą (obecnie zdemontowany). Po wojnie na schronie wzniesiono wieżę Wojsk Ochrony Pogranicza, służącą obecnie jako punkt obserwacji przeciwpożarowej miejscowego nadleśnictwa. Niedaleko schronu znajdują się ruiny schronu najprawdopodobniej elektrowni bateryjnej. "
            ),
        LongPois("BRUHEL2.jpg","Batalionowy Rejon Umocniony nr 2 Hel","54.61533139583248","18.820449455874428","System fortyfikacji polowych mających zapewnić obronę przeciwdesantową od strony morza. Budowę helskiego BRU nr 2 rozpoczęto w maju 1953 r. siłami gdańskiej 5. Brygady Przeciwdesantowej i przekazano do eksploatacji w czerwcu 1956 r. Był to pierwszy tego typu system umocnień i posłużył jako wzór dla ośmiu podobnych zbudowanych w innych rejonach naszego wybrzeża. BRU nr 2 zajmował południowo-wschodnią część półwyspu, rozciągał się wzdłuż wybrzeża na długości niemal 4 km i wchodził wgłąb lądu na szerokość do 1,5 km. BRU tworzyły lekkie fortyfikacje zbudowane z prefabrykatów żelbetowych: główne i zapasowe stanowiska dowodzenia dowódców batalionu, kompanii i plutonów, punkty obserwacyjne, batalionowe punkty medyczne, punkty amunicyjne, schrony dla żołnierzy, stanowiska ogniowe dla czołgów, dział samobieżnych, dział polowych i przeciwlotniczych, moździerzy, stanowiska ogniowe dla ręcznych i ciężkich karabinów maszynowych a także przeszkody przeciwpancerne i przeciwpiechotne. Największą częścią systemu były rowy strzeleckie i transzeje obłożone betonowymi płytami. Na helskim BRU tworzą one trzy linie obrony, a ich łączna długość wynosiła ok. 14,5 km."
            ),
        LongPois("22DYWRAKIETOWYWLOP.jpg","22. Dywizjon Rakietowy WLOP","54.61441721369485","18.814303798203582","Formowanie jednostki rozpoczęto w 1963 r. Dywizjon uzbrojono w 6 wyrzutni kierowanych rakiet przeciwlotniczych systemu S-75M „Wołchow” (mogących także razić cele nawodne), które rozmieszczono w ziemnych obwałowaniach wokół obsypanego ziemią schronu mieszczącego stanowisko dowodzenia, garaże na pojazdy z wyposażeniem, gazoszczelny schron przeciwlotniczy oraz pomieszczenia socjalno-bytowe obsługi. Na schronie znajduje się stanowisko radaru naprowadzania rakiet. Dywizjon chroniony był przed bezpośrednim atakiem lotniczym przez baterię małokalibrowej artylerii przeciwlotniczej, której stanowiska rozrzucone są po terenie dywizjonu. W północno-wschodniej części, tuż przy pozostałościach ogrodzenia, można zobaczyć zachowane stanowiska postojowe dla radarów i pojazdów 214. kompanii radiotechnicznej przeniesionej do Helu w 1978 r. z Lisewa. Nieco dalej, już za ogrodzeniem, znajdują się dwa zachowane z 6 stanowisk ogniowych armat S-60 kal. 57 mm dla 76. baterii artylerii przeciwlotniczej, wykonanych z betonowych prefabrykatów. 22. dywizjon, który wchodził w skład 4. Brygady Rakietowej OP, rozformowano we wrześniu 2001 r. W tymże roku rozformowano również 214. kompanię radiotechniczną. W 2010 roku obiekty dywizjonu rozebrano, zachowując jedynie ziemne obwałowania stanowisk rakiet."
            ),
        LongPois("MUZEUM_SCHLESWIG.jpg","Najcięższa Bateria Artylerii Nadbrzeżnej Schleswig-Holstein","54.623071049816154","18.801718467398256","Budowę baterii, będącej największym obiektem tego typu na Bałtyku, rozpoczęto w końcu 1940 r. W jej skład weszły trzy żelbetowe schrony koszarowo-bojowe na których ustawiono armaty kal. 406 mm, dwa schrony amunicyjne oraz 25-metrowa, 9-kondygnacyjna wieża kierowania ogniem z umieszczonym na stropie dalmierzem. Bateria „Schleswig-Holstein” weszła do służby jako 2 Schwere Artillerie Batterie (2. ciężka bateria artylerii) i wchodziła w skład stacjonującego w Helu Marine Artillerie Abteilung 119 (119. Oddziału Artylerii Marynarki). Pomiędzy październikiem 1941 r., a majem 1942 r. armaty oraz pozostałe wyposażenie zdemontowano i przetransportowano do Francji w rejon Calais, gdzie bateria, pod nazwa zmienioną na „Lindemann”, funkcjonowała jako element Wału Atlantyckiego. Po wojnie w wieży kierowania ogniem funkcjonował przez pewien czas punkt obserwacji dwubocznej 13. BAS, a północny schron koszarowo-bojowy został zaadaptowany przez wojsko na magazyn płodów rolnych, w którym wykonano prowizoryczny strop nad stanowiskiem armaty. Obecnie środkowy schron koszarowo-bojowy (stanowisko „Bruno”) oraz wieża kierowania ogniem zostały zaadaptowane na potrzeby Muzeum Obrony Wybrzeża i są udostępnione do zwiedzania. ",
            ),
        LongPois("ELEKTROWNIARUH.jpg","Elektrownia Rejonu Umocnionego Hel","54.62688381558725","18.789661755818205","Budowę obiektu rozpoczęto w 1935 r., a oddano do użytku wiosną 1939 r. Miała zapewnić dostawę elektryczności dla potrzeb wojska, jak i dla odbiorców cywilnych. Prąd wytwarzany był przy pomocy dwóch agregatów o mocy 400 KM oraz jednego o mocy 100 KM. Elektrownia zaopatrywała w energię również pobliską Jastarnię. Obiekt niedostępny do zwiedzania i administrowany przez wojsko.",
            ),
        LongPois("PKT_OBS.jpg","Punkt obserwacyjny","54.61476338675549","18.82229929820359","Punkt obserwacyjny MW czynny od 1920 r., został w roku 1928 zmodernizowany przez dobudowanie drewnianej wieży i wartowni. Rozbudowany przez Niemców po zajęciu Półwyspu. W pobliżu na wysokiej (15 m n.p.m.) wydmie nazywanej Bocianie Gniazdo lub potocznie „bocianica” przed wojną funkcjonował punkt widokowy stanowiący atrakcję dla letników. Wieża konstrukcji drewnianej stała jeszcze w latach 50-tych XX w. Obecnie zachowały się jedynie betonowe fundamenty składające się z 9 kwadratowych bloków. Po wojnie w bezpośrednim sąsiedztwie wieży zbudowano fragment Batalionowego Rejonu Umocnionego.",
           ),
        LongPois("BATD_SKA.jpg","Bateria Artylerii nr 33 \"Duńska\"","54.617684076054765","18.82369119820373","Oddana do użytku w 1932 r. uzbrojona była w dwie armaty kal. 105 mm firmy Schneider, które zaadaptowano na potrzeby baterii nadbrzeżnej poprzez ustawienie na betonowych działobitniach. W pobliżu stanowisk ogniowych wybudowano dwa schrony dla załogi. W trakcie kampanii wrześniowej prowadziła ostrzał lekkich sił nawodnych Kriegsmarine. Wraz z baterią „grecką” były pierwszymi stałymi elementami obrony wybrzeża na Półwyspie Helskim. Podczas okupacji Kriegsmarine zaadaptowała działobitnie i schrony na potrzeby własnej baterii nadbrzeżnej. Obiekty baterii zostały odnowione przez Muzeum Obrony Wybrzeża w 2011 roku.",
           ),
        LongPois("BAT2DYW60PLOT.jpg","Bateria 2. Dywizjonu 60. Samodzielnego Pułku Artylerii Przeciwlotniczej Marynarki Wojennej","54.62240418981283","18.82100400691485","Zbudowana z betonowych elementów prefabrykowanych pod koniec 1957 r. W skład baterii wchodziły 4 stanowiska ogniowe dla armat przeciwlotniczych kalibru 85 mm, ze schronami załogi. Obecnie zachowane szczątkowo.",
           ),
        LongPois("BATPLOTSZWEDZKA.jpg","Bateria Przeciwlotnicza \"Szwedzka górka\"","54.62262470075269","18.820486340596105","Niemiecka tzw. zaporowa bateria przeciwlotnicza zbudowana ok. 1943 r. Zadaniem baterii była ochrona podejść do Zatoki Gdańskiej, a rozmieszczenie stanowisk ogniowych umożliwiało zarówno walkę z samolotami jak i mniejszymi okrętami przeciwnika. Obecnie zachował się jedynie fundament po jednym z czterech dział kalibru 105 mm, cokół dalmierza, betonowy fundament pod działko kalibru 20 mm. Po wojnie ustawiono tutaj baterię przeciwlotniczą.",
            ),
        LongPois("PPOBS3BAS.jpg","Prawy Punkt Obserwacji Dwubocznej 3. Baterii Artylerii Stałej","54.62661376996075","18.819393632183544","Stanowisko zbudowane około 1957 r., służyło do obserwacji i awaryjnego kierowania ogniem 3 BAS. Punkt wzniesiono tuż przy autonomicznej latarni morskiej „Szwedzka Górka\". Podobny obiekt zbudowano na lewym skrzydle baterii, niedaleko Juraty.",
            ),

        LongPois("115BATERIAPLOTMW.jpg","115. Bateria Przeciwlotnicza Marynarki Wojennej","54.637436303677376","18.80463915587521","Zbudowana w 1974 r. W skład baterii wchodziło 6 stanowisk ogniowych z batonowych elementów prefabrykowanych dla armat plot. wz. 1939 kal. 37 mm rozmieszczonych na okręgu, w rejonie ronda wykorzystywanego wcześniej przez prawy reflektor 3. BAS. Baterię rozformowano na początku 1990 r. W 2011 roku obiekty baterii rozebrano.",
           ),
        LongPois("3BAS.jpg","3. Bateria Artylerii Stałej","54.63762744956966","18.80074626952667","Największa powojenna polska bateria nadbrzeżna, uzbrojona w cztery działa radzieckie typu MU-2 kal. 152 mm. Budowę prowadzono w latach 1955-1958, kiedy to powstały cztery stanowiska ogniowe ze schronami załogi, schron centrali artyleryjskiej oraz główny i zapasowy punkt kierowania ogniem oraz szereg obiektów pomocniczych: schrony reflektorów, magazyn amunicyjny, elektrownię i schron załogi. Na skrzydłach baterii postawiono punkty obserwacji dwubocznej w rejonie Juraty i Góry Szwedów. Baterię rozformowano w 1977 r., a jedyne ocalałe działo kal. 152 mm można oglądać w Muzeum Wojska Polskiego w Warszawie. W 2011 roku część obiektów baterii rozebrano, a pozostałe zostały zasypane i są obecnie niedostępne. Wartymi zobaczenia są dwie zachowane pancerne kopuły dla dalmierzy.",
            ),
        LongPois("BATART34.jpg","Bateria Artylerii nr 34","54.647141741459286","18.788801814824204","Zbudowana już w czasie kampanii wrześniowej w sposób improwizowany. Jej uzbrojenie stanowiły dwa działa kal. 120 mm firmy Bofors (jedno pojedyncze, a drugie podwójne), zdjęte z zatopionego 3 września w porcie helskim, stawiacza min ORP „Gryf\". Gotowość bojową osiągnęła tuż przed kapitulacją Helu i nie zdążyła wziąć udziału w walkach. Oddano jedynie kilka strzałów technicznych. Do naszych czasów zachowały się obie betonowe podstawy z pierścieniami służącymi do mocowania armat odkryte w 2006 roku przez miłośników fortyfikacji.",
            ),


        LongPois("tlo.jpg","Posterunek Plutonu Rozpoznania Radioelektronicznego","54.647414884409706","18.786130334786442","Posterunek plutonu 14 Batalionu Rozpoznania Radioelek¬tronicznego zlikwidowany w latach 90. Na szczycie wydmy znajduje się wybetonowane stanowisko, na którym stały pojazdy i przyczepy z wyposażeniem. Poniżej ustawiono niewielka wiatę. W 2011 roku pozostałości posterunku rozebrano.",
            ),


        LongPois("BATGRECKA.jpg","Bateria Artylerii nr 32 \"Grecka\"","54.64046892950777","18.801123008136145","Oddana do użytku w 1932 r., uzbrojona była w dwie polowe armaty kal. 105 mm firmy Schneider, które zaadoptowano na potrzeby baterii nadbrzeżnej poprzez ustawienie na betonowych działobitniach. W pobliżu stanowisk ogniowych wybudowano dwa schrony dla załogi. W czasie kampanii wrześniowej armaty przetransportowano w okolice Juraty w celu wsparcia obrońców od strony nasady półwyspu. Wraz z baterią „duńską\\\" były pierwszy¬mi stałymi elementami obrony wybrzeża na Półwyspie Helskim. Podczas okupacji Kriegsmarine zaadaptowała działobitnie i schrony na potrzeby własnej baterii nadbrzeżnej. Obiekty baterii znajdują się przy utwardzonej drodze w rejonie Hel-Bór.\",\n",
            ),
        LongPois("MAGAZYNY_TORPED.jpg","Magazyn torped","54.595660407136165","18.808104214431733","Wybudowane w 1936 r. w formie czterech żelbetowych schronów częściowo zagłębionych w ziemi, przez które poprowadzono tory kolejki wąskotorowej. Obiekt niedostępny, nadal użytkowany przez wojsko.",
            ),
        LongPois("MAGAZYNYMIN.jpg","Magazyny min morskich","54.6453432489814","18.779727859570915","Budowę rozpoczęto w 1933 r. Wybudowano trzy hale magazynowe, częściowo zagłębione w ziemi oraz budynek trotylarni. Przez każdą z hal poprowadzono tor kolejki wąskotorowej, za pomocą której transportowano miny do nabrzeży portowych. Obiekt niedostępny, nadal użytkowany przez wojsko.",
            ),
        LongPois("ZBIORNIKIPALIW.jpg","Zbiorniki paliw płynnych","54.65192281320633","18.780253428637103","Dwa zbiorniki o pojemności po 1000 ton dla paliwa ciężkiego stosowanego w okrętach nawodnych. W latach 1934-35 zdołano wybudować tylko same zbiorniki i rurociąg do portu. Przed wojną zabrakło czasu na wykonanie stacji pomp oraz innych instalacji. Podczas okupacji obiekt został zmodernizowany i wykorzystywany na potrzeby Kriegsmarine. Po wojnie ponownie rozbudowany. Obiekty są niedostępne, nadal użytkowane przez wojsko.",
            ),
        LongPois("BATPLOTHELBOR.jpg","Bateria Przeciwlotnicza \"Hel-Bór\"","54.65517692196562","18.77472981722428","Niemiecka tzw. zaporowa bateria przeciwlotnicza (Sperrbatterie) zbudowana ok. 1940 r. Uzbrojona w cztery działa S.K. C/32 kalibru 105 mm ustawione na betonowych fundamentach. Zadaniem baterii była ochrona podejść do Zatoki Gdańskiej, a rozmieszczenie stanowisk ogniowych umożliwiało zarówno walkę z samolotami jak i mniejszymi okrętami przeciwnika. W pobliżu wzniesiono dwukondygnacyjny schron kierowania ogniem wyposażony w dalmierz zamontowany na stropie pod pancerną kopułą (obecnie zdemontowany). Za schronem na wydmie zachował się fundament po wieży konstrukcji drewnianej, na której zamontowane było działko szybkostrzelne kalibru 20 mm, służące bezpośredniej obronie baterii. W 1943 r. obiekt obsadziła jedna z baterii 818. morskiego oddziału artylerii plot (Marinę Flak Abteilung 818) przeniesionego na Półwysep z Lorient we Francji.",
           ),
        LongPois("23BATERIAPLOT.jpg","23. Bateria Przeciwlotnicza","54.655306418529776","18.76936579830346","Wraz z bliźniaczymi bateriami 21 i 22 wchodziła w skład 2 Morskiego Dywizjonu Artylerii Przeciwlotniczej. Uzbrojona była w dwie armaty kalibru 75 mm firmy Schneider, ustawione na stropach schronów amunicyjnych zbudowanych w latach 1933-35. U podnóża wydmy, na której stały armaty znajdował się obecnie zniszczony i zasypany schron amunicyjny. Podstawowym zadaniem baterii w trakcie kampanii wrześniowej była obrona przeciwlotnicza rejonu Hel-Bór, w tym znajdujących się niedaleko podziemnych zbiorników paliwa oraz ewentualna obrona przeciwdesantowa. Podczas okupacji Kriegsmarine zaadaptowała schrony na potrzeby własnej baterii przeciwlotniczej. Bateria znajduje się w pasie wydm w rejonie Hel-Bór, tuż przy torach kolejowych.",
            ),


        LongPois("64DYWRAKIETOWYWLOP.jpg","64. Dywizjon Rakietowy WLOP","54.595660407136165","18.808104214431733","Formowanie jednostki rozpoczęto w 1973 r. Dywizjon uzbrojono w 4 kierowane rakiety przeciwlotnicze systemu S-125M „Newa” (mogące także razić cele nawodne), których wyrzutnie rozmieszczono wachlarzem skierowanym na morze. Pomiędzy wyrzutniami znajduje się obsypany ziemią schron mieszczący stanowisko dowodzenia, garaże na pojazdy z wyposażeniem, gazoszczelny schron przeciwlotniczy oraz pomieszczenia socjalno-bytowe obsługi. Na stropie schronu umieszczono stanowisko radaru naprowadzania rakiet. Na terenie dywizjonu powstało ponadto szereg innych obiektów jak schron magazyn rakiet, betonowe boksy-garaże każdy dla dwóch samochodów transportowo-załadowczych z zapasowymi rakietami, szereg małych schronów przeciwlotniczych itd. Dywizjon, który wchodził w skład 4 Brygady Rakietowej OP, rozformowano we wrześniu 1993 r. W 2011 roku obiekty dywizjonu całkowicie rozebrano.",
            ),


        LongPois("LPOBS3BAS.jpg","Lewy Punkt Obserwacji Dwubocznej 3. Baterii Artylerii Stałej","54.679459246384276","18.734926183440574","Stanowisko, zbudowane około 1957 r., służyło do obserwacji i awaryjnego kierowania ogniem 3. BAS. Obok ażurowej, żelbetowej wieży obserwacyjnej zbudowano także schron dla załogi stanowiska. Podobny obiekt, jednak bez schronu załogi, zbudowano w rejonie Góry Szwedów.",
           ),
        LongPois("MAGAZYNYAMUN.jpg","Magazyny amunicji","54.63885659627331","18.775781511872815","Wybudowane w 1937 r. w formie obsypanych ziemią hal o długości 30 m każda (łącznie 11 obiektów), przez środek których prowadził tor kolejki wąskotorowej. W magazynach przechowywano amunicję artyleryjską dla okrętów. W trakcie kampanii wrześniowej w jednym ze schronów mieściło się dowództwo Rejonu Umocnionego „Hel”. Obiekt niedostępny, nadal użytkowany przez wojsko",
           ),
        LongPois("22BATERIAPLOT.jpg","22. Bateria Przeciwlotnicza","54.629947055292554","18.777229237985956","Tzw. „bateria portowa\". Wraz z bliźniaczymi bateriami 21 i 23 wchodziła w skład 2 Morskiego Dywizjonu Artylerii Przeciwlotniczej. Uzbrojona była w dwie armaty kalibru 75 mm firmy Schneider ustawione na stropach schronów amunicyjnych zbudowanych w łatach 1933-35. Ok. 150 m na północ od stanowisk baterii stała wybudowana w 1936 r. wieża obserwacyjna dowódcy 2. MDAPlot. (tzw. „wieża Wojcieszka\", swoją potoczną nazwę wzięła od nazwiska dowódcy dywizjonu, kpt. mar. Mariana Wojcieszka), po której zachowały się betonowe fundamenty. Podstawowym zadaniem baterii w trakcie kampanii wrześniowej była obrona przeciwlotnicza portu wojennego oraz ewentualna obrona przeciwdesantowa. Podczas okupacji Kriegsmarine zaadaptowała schrony na potrzeby własnej baterii przeciwlotniczej. Obecnie bateria znajduje się na wysokiej wydmie w północno-zachodniej części Ośrodka Wypoczynkowego „Kormoran”.",
           ),
        LongPois("NIEUK_SCHRONPLOT.jpg","Nieukończony schron przeciwlotniczy","54.61613998868892","18.785217517787196","Budowany dla załóg doświadczalnej jednostki okrętów podwodnych schron typu T-750. Z trzech kondygnacji wybudowano tylko jedną. Posiadał ściany grubości 2,5 m. Obiekt na terenie wojskowym.",
           ),
        LongPois("OOJASTARNIA.jpg","Ośrodek oporu \"Jastarnia\"","54.71646579114099","18.634613571143376","Wybudowany tuż przed wybuchem II wojny światowej, składał się z wysuniętej pozycji polowej, pozycji głównej oraz pozycji tyłowej. Do wybuchu wojny wykonano 4 ciężkie schrony pozycji głównej (o nazwach własnych „Sęp”, „Sokół”, „Sabała”, „Saratoga”), jeden schron lekki pozycji tyłowej oraz fundamenty pod planowane trzy pozostałe. Dwa z ciężkich schronów („Sabała” i „Sęp”) miały zostać wyposażone w pancerne kopuły z armatami przeciwpancernymi kalibru 37 mm, jednak do wybuchu wojny nie dostarczono ich na Półwysep. Obecnie wszystkie schrony zostały odrestaurowane, a w okresie wiosenno-letnim dwa z nich udostępniane są do zwiedzania. Zwiedzanie ułatwia ścieżka dydaktyczna prowadząca wśród umocnień i odtworzonych przeszkód.",
          ),
        LongPois("BATPLOTJASTARNIA.jpg","Bateria Przeciwlotnicza \"Jastarnia\"","54.694025297574804","18.694279711700137","Niemiecka tzw. zaporowa bateria przeciwlotnicza (Sperrbatterie) zbudowana ok. 1940 r. Uzbrojona w cztery działa kal. 105 mm ustawione na betonowych fundamentach. Zadaniem baterii była ochrona podejść do Zatoki Gdańskiej, a rozmieszczenie stanowisk ogniowych umożliwiało zarówno walkę z samolotami jak i mniejszymi okrętami przeciwnika. Za torami na wysokiej wydmie zbudowano dwukondygnacyjny schron kierowania ogniem wyposażony w dalmierz zamontowany pod pancerną kopułą. Na skrzydłach baterii zachowały się fundamenty po dwóch wieżach, na których zamontowane były działka szybkostrzelne kalibru 70 mm służące bezpośredniej obronie baterii oraz stanowisko małego radaru.",
         ),
        LongPois("WRAKI.jpg","Wraki okrętów","54.613967713736166","18.7785620433556","Wraki powojennych niszczycieli ORP Wicher II oraz ORP Grom II zatopione ok. 1977 r. w pobliżu portu wojennego jako elementy falochronu.",
         ),

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
        PRPO_3BAS,
        BATPLOT_115,
        BAS_3,
        BATART_34,
        PPRR,
        BAT_GRECKA,
        MAG_TORP,
        MAG_MIN,
        ZB_PALIW,
        BATPLOT_BOR,
        BATPLOT_23,
        DYWRAK_34,
        LPO_3BAS,
        MAG_AMUN,
        BATPLOT_22,
        NUK_SCHRON,
        OO_JAST,
        BATPLOT_JAST,
        WRAKI


    }
}

data class LongPois(
    val img: String,
    val name: String,
    val lat: String,
    val lng: String,
    val desc: String
)

