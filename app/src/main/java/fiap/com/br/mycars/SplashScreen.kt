package fiap.com.br.mycars

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.lang.Exception

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val sharedPreferences = getSharedPreferences("MyCars", Context.MODE_PRIVATE)

        Handler().postDelayed({
            val email = sharedPreferences.getString("email", "")
            if(email.isNotBlank()) {
                val intent = Intent(baseContext, MainScreen::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            } else {
                val intent = Intent(baseContext, LoginScreen::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            }
        }, 5000)
    }
}
