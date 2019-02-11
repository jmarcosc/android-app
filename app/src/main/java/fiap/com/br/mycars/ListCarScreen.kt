package fiap.com.br.mycars

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import fiap.com.br.mycars.adapters.CarsAdapter
import fiap.com.br.mycars.webservices.RetrofitInitializer
import fiap.com.br.mycars.webservices.models.Car
import kotlinx.android.synthetic.main.activity_list_car_screen.*
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.list_cars_layout.*
import kotlinx.android.synthetic.main.list_cars_layout.view.*
import org.json.JSONStringer
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class ListCarScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_car_screen)

        val context = this
        val call = RetrofitInitializer().carService().listCars()
        call.enqueue(object: Callback<List<Car>> {
            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                Log.d("Termianl", t.toString())
                Snackbar.make(findViewById(android.R.id.content), "Ops! Anything is going wrong!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                response?.body()?.let {
                    val cars: List<Car> = it
                    val recyclerView = rcv_listcar
                    recyclerView.adapter = CarsAdapter(cars, context)

                    //val layoutManager = GridLayoutManager(context, 1)
                    val layoutManager = LinearLayoutManager(context)
                    recyclerView.layoutManager = layoutManager

                }
            }
        })

    }
}
