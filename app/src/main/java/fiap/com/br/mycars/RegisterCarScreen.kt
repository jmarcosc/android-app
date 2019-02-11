package fiap.com.br.mycars

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Button
import android.widget.EditText
import fiap.com.br.mycars.webservices.RetrofitInitializer
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterCarScreen : AppCompatActivity() {

    private lateinit var model: EditText
    private lateinit var fuel: EditText
    private lateinit var engine: EditText
    private lateinit var price: EditText
    private lateinit var register: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_car_screen)

        model = findViewById(R.id.edt_regcar_model)
        fuel = findViewById(R.id.edt_regcar_fuel)
        engine = findViewById(R.id.edt_regcar_engine)
        price = findViewById(R.id.edt_regcar_price)
        register = findViewById(R.id.btn_regcar_register)

        register.setOnClickListener {
            val model: String = this.model.text.toString()
            val fuel: String = this.fuel.text.toString()
            val engine: String = this.engine.text.toString()
            val price: Double = this.price.text.toString().toDouble()

            if(model.isNotEmpty() && fuel.isNotEmpty() && engine.isNotEmpty() && !price.isNaN()) {
                val call = RetrofitInitializer().carService().newCar(model, fuel, engine, price)
                call.enqueue(object: Callback<JSONStringer> {
                    override fun onFailure(call: Call<JSONStringer>, t: Throwable) {
                        Log.d("Terminal", t.toString())
                        Snackbar.make(findViewById(android.R.id.content), call.toString(), Snackbar.LENGTH_INDEFINITE)
                            .setAction("Action", null).show()
                    }
                    override fun onResponse(call: Call<JSONStringer>, response: Response<JSONStringer>) {
                        Snackbar.make(findViewById(android.R.id.content), "Car registered!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Action", null).show()
                    }
                })
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Ops! Anything is going wrong!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }
}
