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

class EditCarScreen : AppCompatActivity() {

    private lateinit var id: String
    private lateinit var model: EditText
    private lateinit var fuel: EditText
    private lateinit var engine: EditText
    private lateinit var price: EditText
    private lateinit var register: Button
    private lateinit var delete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_car_screen)

        id = intent.extras.getString("id")
        model = findViewById(R.id.edt_edtcar_model)
        model.setText(intent.extras.getString("model"))
        fuel = findViewById(R.id.edt_edtcar_fuel)
        fuel.setText(intent.extras.getString("fuel"))
        engine = findViewById(R.id.edt_edtcar_engine)
        engine.setText(intent.extras.getString("engine"))
        price = findViewById(R.id.edt_edtcar_price)
        price.setText(intent.extras.getDouble("price").toString())
        register = findViewById(R.id.btn_edtcar_register)
        delete = findViewById(R.id.btn_edtcar_delete)

        register.setOnClickListener {
            val model: String = this.model.text.toString()
            val fuel: String = this.fuel.text.toString()
            val engine: String = this.engine.text.toString()
            val price: Double = this.price.text.toString().toDouble()

            if(model.isNotEmpty() && fuel.isNotEmpty() && engine.isNotEmpty() && !price.isNaN()) {
                val call = RetrofitInitializer().carService().editCar(model, fuel, engine, price, id)
                call.enqueue(object: Callback<JSONStringer> {
                    override fun onFailure(call: Call<JSONStringer>, t: Throwable) {
                        Log.d("Terminal", t.toString())
                        Snackbar.make(findViewById(android.R.id.content), call.toString(), Snackbar.LENGTH_INDEFINITE)
                            .setAction("Action", null).show()
                    }
                    override fun onResponse(call: Call<JSONStringer>, response: Response<JSONStringer>) {
                        val intent = Intent(baseContext, ListCarScreen::class.java)
                        startActivity(intent)
                    }
                })
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Ops! Anything is going wrong! Confirm your data!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

        delete.setOnClickListener {
            val call = RetrofitInitializer().carService().deleteCar(id)
            call.enqueue(object: Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("Terminal", t.toString())
                    Snackbar.make(findViewById(android.R.id.content), call.toString(), Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show()
                }
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Snackbar.make(findViewById(android.R.id.content), "Car deleted!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show()
                    val intent = Intent(baseContext, ListCarScreen::class.java)
                    startActivity(intent)
                }
            })
        }

    }
}
