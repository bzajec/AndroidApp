package hr.algebra.football_projekt

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.football_projekt.framework.setBooleanPreference
import hr.algebra.football_projekt.framework.startActivity

class FootballReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED, true)
        context.startActivity<HostActivity>()
    }
}
