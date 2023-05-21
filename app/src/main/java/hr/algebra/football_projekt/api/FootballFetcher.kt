package hr.algebra.football_projekt.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.algebra.football_projekt.FOOTBALL_PROVIDER_CONTENT_URI
import hr.algebra.football_projekt.FootballReceiver
import hr.algebra.football_projekt.framework.sendBroadcast
import hr.algebra.football_projekt.handler.downloadImageAndStore
import hr.algebra.football_projekt.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FootballFetcher (private val context: Context) {

    private var footballApi : FootballApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        footballApi = retrofit.create(FootballApi::class.java)
    }

    fun fetchItems(){
        val request = footballApi.fetchItems()
        request.enqueue(object: Callback<List<FootballItem>> {

            override fun onResponse(
                call: Call<List<FootballItem>>,
                response: Response<List<FootballItem>>
            ) {
                if (response.body() != null){
                    populateItems(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<FootballItem>>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }
        })
    }

    private fun populateItems(footballItems: List<FootballItem>) {

        GlobalScope.launch {
            footballItems.forEach{
                val picturePath = downloadImageAndStore(context, it.thumbnail, it.title.replace(" ","_"))
                var values = ContentValues().apply {
                    put(Item::title.name, it.title)
                    put(Item::description.name, it.short_description)
                    put(Item::picturePath.name, picturePath ?: "")
                    put(Item::date.name, it.release_date)
                    put(Item::genre.name, it.genre)
                    put(Item::platform.name, it.platform)
                    put(Item::publisher.name, it.publisher)
                    put(Item::developer.name, it.developer)
                    put(Item::gameUrl.name, it.game_url)
                    put(Item::moreUrl.name, it.freetogame_profile_url)

                }

                context.contentResolver.insert(FOOTBALL_PROVIDER_CONTENT_URI, values)

            }
            context.sendBroadcast<FootballReceiver>()
        }

    }

}