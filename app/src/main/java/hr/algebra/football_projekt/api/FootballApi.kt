package hr.algebra.football_projekt.api

import retrofit2.Call
import retrofit2.http.GET

const val API_URL ="https://www.freetogame.com/api/";

interface FootballApi {

    @GET("games")
    fun fetchItems() : Call<List<FootballItem>>

}