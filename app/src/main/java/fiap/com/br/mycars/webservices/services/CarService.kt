package fiap.com.br.mycars.webservices.services

import fiap.com.br.mycars.webservices.models.Car
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.http.*

interface CarService {

    @GET("/cars")
    fun listCars(): Call<List<Car>>

    @POST("/cars")
    @FormUrlEncoded
    fun newCar(
        @Field("model") model: String,
        @Field("fuel") fuel: String,
        @Field("engine") engine: String,
        @Field("price") price: Double
    ): Call<JSONStringer>

    @PUT("/cars/{id}")
    @FormUrlEncoded
    fun editCar(
        @Field("model") model: String,
        @Field("fuel") fuel: String,
        @Field("engine") engine: String,
        @Field("price") price: Double,
        @Path("id") id: String
    ): Call<JSONStringer>

    @DELETE("/cars/{id}")
    fun deleteCar(
        @Path("id") id: String
    ): Call<Void>

}