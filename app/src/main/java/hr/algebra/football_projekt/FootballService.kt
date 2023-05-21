package hr.algebra.football_projekt


import android.content.Context
import android.content.Intent
import android.app.Service
import android.app.job.JobService
import androidx.core.app.JobIntentService
import hr.algebra.football_projekt.api.FootballFetcher
import hr.algebra.football_projekt.framework.sendBroadcast

private const val JOB_ID = 1

class FootballService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        FootballFetcher(this).fetchItems()
    }
    companion object{
        fun enqueueWork(context: Context, intent: Intent){
            enqueueWork(context, FootballService::class.java, JOB_ID, intent)
        }
    }
}