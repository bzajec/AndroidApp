package hr.algebra.football_projekt.model

class Item (

    val _id: Int, // _ zbog sqlite
    val title: String,
    val description: String,
    val picturePath: String,
    val date: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val gameUrl : String,
    val moreUrl : String

)