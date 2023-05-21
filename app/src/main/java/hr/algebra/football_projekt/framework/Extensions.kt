package hr.algebra.football_projekt.framework

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.animation.AnimationUtils
import androidx.preference.PreferenceManager
import hr.algebra.football_projekt.FOOTBALL_PROVIDER_CONTENT_URI
import hr.algebra.football_projekt.model.Item


fun View.applyAnimation(resourceId: Int)
        = startAnimation(AnimationUtils.loadAnimation(context, resourceId))

inline  fun<reified T: Activity> Context.startActivity() = startActivity(Intent(this, T::class.java))
inline  fun<reified T: Activity> Context.startActivity(key: String, value: Int)
        = startActivity(Intent(this, T::class.java).apply { putExtra(key,value) })
inline  fun<reified T: BroadcastReceiver> Context.sendBroadcast() = sendBroadcast(Intent(this, T::class.java))

fun Context.setBooleanPreference(key: String, value: Boolean) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()

fun Context.getBooleanPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this).getBoolean(key, false)

fun Context.isOnline() : Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    if (network != null){
        var networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        if (networkCapabilities != null){
            return  networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }
    return false
}

fun Context.fetchItems() : MutableList<Item> {
    val items = mutableListOf<Item>()
    val cursor = contentResolver?.query(FOOTBALL_PROVIDER_CONTENT_URI, null, null, null, null)

    if (cursor != null){
        while (cursor.moveToNext()){
            items.add(Item(
                cursor.getInt(cursor.getColumnIndex(Item::_id.name)),
                cursor.getString(cursor.getColumnIndex(Item::title.name)),
                cursor.getString(cursor.getColumnIndex(Item::description.name)),
                cursor.getString(cursor.getColumnIndex(Item::picturePath.name)),
                cursor.getString(cursor.getColumnIndex(Item::date.name)),
                cursor.getString(cursor.getColumnIndex(Item::genre.name)),
                cursor.getString(cursor.getColumnIndex(Item::platform.name)),
                cursor.getString(cursor.getColumnIndex(Item::publisher.name)),
                cursor.getString(cursor.getColumnIndex(Item::developer.name)),
                cursor.getString(cursor.getColumnIndex(Item::gameUrl.name)),
                cursor.getString(cursor.getColumnIndex(Item::moreUrl.name))

            ))
        }
    }
    return items
}