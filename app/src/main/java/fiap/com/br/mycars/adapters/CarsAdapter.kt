package fiap.com.br.mycars.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import fiap.com.br.mycars.EditCarScreen
import fiap.com.br.mycars.ListCarScreen
import fiap.com.br.mycars.R
import fiap.com.br.mycars.webservices.models.Car
import kotlinx.android.synthetic.main.list_cars_layout.view.*

class CarsAdapter(

    private val cars: List<Car>,
    private val context: Context) : Adapter<CarsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return cars.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = cars[position]

        holder.let {
            it.bindview(car)
        }

    }

    interface ClickListener {
        fun onClick(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_cars_layout, parent, false)

        view.setOnClickListener {
            val intent = Intent(this.context, EditCarScreen::class.java)
            
            intent.putExtra("id", it.txv_listcar_id.text.toString())
            intent.putExtra("model", it.txv_listcar_model.text.toString())
            intent.putExtra("fuel", it.txv_listcar_fuel.text.toString())
            intent.putExtra("engine", it.txv_listcar_engine.text.toString())
            intent.putExtra("price", it.txv_listcar_price.text.toString().toDouble())

            context.startActivity(intent)
        }

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id = itemView.txv_listcar_id
        val model = itemView.txv_listcar_model
        val fuel = itemView.txv_listcar_fuel
        val engine = itemView.txv_listcar_engine
        val price = itemView.txv_listcar_price

        fun bindview(car: Car) {

            id.text = car._id
            model.text = car.model
            fuel.text = car.fuel
            engine.text = car.engine
            price.text = car.price.toString()

        }

    }

}