package valeria.moscoso.goliathnationalbankapp.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import valeria.moscoso.goliathnationalbankapp.R
import valeria.moscoso.goliathnationalbankapp.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}