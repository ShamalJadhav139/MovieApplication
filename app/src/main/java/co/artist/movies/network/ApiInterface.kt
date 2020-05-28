package co.artist.info.network
import com.google.gson.JsonObject
import retrofit2.http.GET


interface ApiInterface {
    @GET("movie/list")
    fun getGenreList(
        @retrofit2.http.Query("api_key") api_key: String
    ): retrofit2.Call<JsonObject>



}
