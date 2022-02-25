package first.android.cis.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://192.168.215.127:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val newsApi: NewsApi =
        retrofit.create(NewsApi::class.java)
}