package com.example.wbunkry.database

import android.graphics.Point

object ShortPathDB {

    val poiList: ArrayList<Pois> = arrayListOf<Pois>(
        Pois("ma27.png","Nazwa pierwszego obiektu","59.888888","18.656989","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla at ex vel diam aliquam ultrices. Pellentesque elit metus, dignissim at vestibulum id, egestas ut velit. Duis enim nunc, dictum eget consectetur sit amet, mollis in nisl. Suspendisse sollicitudin urna vel diam dictum, scelerisque condimentum ante interdum. Donec ut rutrum mi. Maecenas tincidunt tempus felis vel rutrum. Nulla quis erat ultricies, eleifend nisl eu, rhoncus diam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam erat volutpat. Pellentesque sed ligula tortor. Etiam ipsum ex, consequat sed risus id, volutpat ullamcorper enim.\n" +
                "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla at ex vel diam aliquam ultrices. Pellentesque elit metus, dignissim at vestibulum id, egestas ut velit. Duis enim nunc, dictum eget consectetur sit amet, mollis in nisl. Suspendisse sollicitudin urna vel diam dictum, scelerisque condimentum ante interdum. Donec ut rutrum mi. Maecenas tincidunt tempus felis vel rutrum. Nulla quis erat ultricies, eleifend nisl eu, rhoncus diam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam erat volutpat. Pellentesque sed ligula tortor. Etiam ipsum ex, consequat sed risus id, volutpat ullamcorper enim.\n" +
                "Sed sem purus, vestibulum ac libero quis, vulputate sagittis arcu. Duis consequat auctor odio vestibulum efficitur. Aenean eget rhoncus ante. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Vestibulum congue nulla id est dignissim sollicitudin. Praesent dui neque, tincidunt eu molestie vitae, scelerisque eu nibh. Vestibulum cursus lectus ut nulla commodo, vel congue erat luctus. Vestibulum convallis sagittis laoreet. Donec dictum sagittis ullamcorper. Cras sit amet dictum nibh, non fringilla nunc. Sed aliquet iaculis sem, in tempor quam facilisis nec.\n" +
                "\n" +
                "Donec ac maximus tortor, nec vulputate augue. Donec at blandit dui. Integer id nibh ornare turpis laoreet condimentum. Nullam mattis eleifend ultricies. Aliquam erat volutpat. Nunc non leo sed risus sagittis dignissim. Vivamus blandit magna arcu, eget rhoncus felis elementum malesuada. Sed facilisis ex et efficitur aliquam. Mauris tortor sem, tincidunt sed diam mollis, pharetra efficitur nisl. Nulla luctus sapien metus, non molestie nulla ullamcorper et. Sed quis quam a sapien elementum bibendum. Fusce eleifend commodo neque, blandit ultricies dolor tincidunt eget. Aliquam eleifend ornare tellus quis blandit.\n"),
        Pois("tlo.jpg","Nazwa drugiego obiektu","59.786700","18.569856","desc2"),
        Pois("tlo1b.jpg","Nazwa trzeciego obiektu","58.888901", "18.482521","desc3")

            )
}

data class Pois(
    val img: String,
    val name: String,
    val lat: String,
    val lng: String,
    val desc: String
)