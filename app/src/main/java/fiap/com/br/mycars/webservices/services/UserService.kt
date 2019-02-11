package fiap.com.br.mycars.webservices.services

import fiap.com.br.mycars.webservices.models.User
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {

    @POST("/users")
    @FormUrlEncoded
    fun newUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<JSONStringer>

    @POST("/users/login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<User>

}