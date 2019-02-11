package fiap.com.br.mycars

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val sharedPreferences = getSharedPreferences("MyCars", Context.MODE_PRIVATE)

        val btnRegisterCar = findViewById<Button>(R.id.btn_main_registercar)
        btnRegisterCar.setOnClickListener {
            val intent = Intent(baseContext, RegisterCarScreen::class.java)
            startActivity(intent)
        }

        val btnListCars = findViewById<Button>(R.id.btn_main_listcars)
        btnListCars.setOnClickListener {
            val intent = Intent(baseContext, ListCarScreen::class.java)
            startActivity(intent)
        }

        val btnAbout = findViewById<Button>(R.id.btn_main_about)
        btnAbout.setOnClickListener {
            val intent = Intent(baseContext, AboutScreen::class.java)
            startActivity(intent)
        }

        val btnSignOut = findViewById<Button>(R.id.btn_main_signout)
        btnSignOut.setOnClickListener {
            val pref = sharedPreferences.edit()
            pref.putString("email", "")
            pref.apply()
            val intent = Intent(baseContext, LoginScreen::class.java)
            startActivity(intent)
        }

    }
}
