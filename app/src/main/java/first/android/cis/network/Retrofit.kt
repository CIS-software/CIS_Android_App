package first.android.cis.network

import first.android.cis.network.newsAPI.NewsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://192.168.100.8:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val newsApi: NewsApi =
        retrofit.create(NewsApi::class.java)

}