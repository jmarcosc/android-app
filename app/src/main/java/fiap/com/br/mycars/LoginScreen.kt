package fiap.com.br.mycars

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Button
import android.widget.EditText
import fiap.com.br.mycars.webservices.RetrofitInitializer
import fiap.com.br.mycars.webservices.models.User
import android.util.Log
import android.widget.CheckBox
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginScreen : AppCompatActivity() {

    //private lateinit var util: Utils
    //this.util.isEmailValid(email)
    private lateinit var edt_email: EditText
    private lateinit var edt_password: EditText
    private lateinit var btn_log_in: Button
    private lateinit var btn_sign_up: Button
    private lateinit var cbx_kpconn: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val sharedPreferences = getSharedPreferences("MyCars", Context.MODE_PRIVATE)

        this.edt_email = findViewById(R.id.edt_email)
        this.edt_password = findViewById(R.id.edt_password)
        this.cbx_kpconn = findViewById(R.id.cbx_login_kpconn)
        this.btn_log_in = findViewById(R.id.btn_log_in)
        this.btn_sign_up = findViewById(R.id.btn_sign_up)

        this.btn_log_in.setOnClickListener {
            val email: String = this.edt_email.text.toString()
            val password: String = this.edt_password.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                val call = RetrofitInitializer().userService().login(email, password)
                call.enqueue(object: Callback<User> {
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.d("Terminal", t.toString())
                        Snackbar.make(findViewById(android.R.id.content), call.toString(), Snackbar.LENGTH_INDEFINITE)
                            .setAction("Action", null).show()
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        Log.d("Response", response.toString())
                        response?.body()?.let {
                            val user: User = it
                            if(!response.isSuccessful) {
                                Snackbar.make(findViewById(android.R.id.content), "Ops! Anything is going wrong!", Snackbar.LENGTH_INDEFINITE)
                                    .setAction("Action", null).show()
                            } else if(cbx_kpconn.isChecked) {
                                val pref = sharedPreferences.edit()
                                pref.putString("email", user.email)
                                pref.apply()
                                val intent = Intent(baseContext, MainScreen::class.java)
                                startActivity(intent)
                            } else {
                                val intent = Intent(baseContext, MainScreen::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                })
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Please, inform your login information! Confirm your data!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

        this.btn_sign_up.setOnClickListener {
            val intent = Intent(baseContext, RegisterLoginScreen::class.java)
            startActivity(intent)
        }


    }
}
