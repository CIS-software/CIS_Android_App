package first.android.cis.network

import first.android.cis.network.getNews.NewsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://192.168.215.127:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val newsApi: NewsApi =
        retrofit.create(NewsApi::class.java)

    //TODO: Переделать возврат переменной сверху, сделать как снизу.
    fun <T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

}