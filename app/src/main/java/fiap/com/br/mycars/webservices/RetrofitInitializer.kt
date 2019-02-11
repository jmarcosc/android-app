package fiap.com.br.mycars.webservices

import fiap.com.br.mycars.webservices.services.CarService
import fiap.com.br.mycars.webservices.services.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://android-ws.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun userService () : UserService {
        return retrofit.create(UserService::class.java)
    }

    fun carService () : CarService {
        return retrofit.create(CarService::class.java)
    }
}