package hr.algebra.football_projekt.api

import com.google.gson.annotations.SerializedName

class FootballItem (

    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("thumbnail") val thumbnail : String,
    @SerializedName("short_description") val short_description : String,
    @SerializedName("game_url") val game_url : String,
    @SerializedName("genre") val genre : String,
    @SerializedName("platform") val platform : String,
    @SerializedName("publisher") val publisher : String,
    @SerializedName("developer") val developer : String,
    @SerializedName("release_date") val release_date : String,
    @SerializedName("freetogame_profile_url") val freetogame_profile_url : String
)