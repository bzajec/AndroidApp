package hr.algebra.football_projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import hr.algebra.football_projekt.framework.applyAnimation
import hr.algebra.football_projekt.framework.getBooleanPreference
import hr.algebra.football_projekt.framework.isOnline
import hr.algebra.football_projekt.framework.startActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

private const val DELAY : Long= 3000
const val DATA_IMPORTED = "hr.algebra.football.data_imported"

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        ivSplash.applyAnimation(R.anim.rotate)
        tvSplash.applyAnimation(R.anim.blink)
    }

    private fun redirect() {
        if (getBooleanPreference(DATA_IMPORTED)) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity<HostActivity>()
            }, DELAY)
        } else  {
            if (isOnline()){
                //start service
                Intent(this, FootballService::class.java).apply {
                    FootballService.enqueueWork(this@SplashScreenActivity,this)
                }
            } else {
                Toast.makeText(this, getString(R.string.please_connect_to_internet), Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

}
