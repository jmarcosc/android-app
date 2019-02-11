package fiap.com.br.mycars

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Button
import android.widget.EditText
import fiap.com.br.mycars.webservices.RetrofitInitializer
import android.util.Log
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterLoginScreen : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var passconf: EditText
    private lateinit var reguser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_login_screen)

        email = findViewById(R.id.edt_reguser_email)
        password = findViewById(R.id.edt_reguser_password)
        passconf = findViewById(R.id.edt_reguser_passconf)
        reguser = findViewById(R.id.btn_reguser)

        reguser.setOnClickListener {
            if(isEmailValid(email.text.toString()) == true && email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty() && passconf.text.toString().isNotEmpty() && (password.text.toString().equals(passconf.text.toString()))) {
                val call = RetrofitInitializer().userService().newUser(email.text.toString(), password.text.toString())
                call.enqueue(object: Callback<JSONStringer> {
                    override fun onFailure(call: Call<JSONStringer>, t: Throwable) {
                        Log.d("Terminal", t.toString())
                        Snackbar.make(findViewById(android.R.id.content), call.toString(), Snackbar.LENGTH_INDEFINITE)
                            .setAction("Action", null).show()
                    }
                    override fun onResponse(call: Call<JSONStringer>, response: Response<JSONStringer>) {
                        Snackbar.make(findViewById(android.R.id.content), "User registered!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Action", null).show()
                        val intent = Intent(baseContext, LoginScreen::class.java)
                        startActivity(intent)
                    }
                })
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Ops! Anything is going wrong!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
