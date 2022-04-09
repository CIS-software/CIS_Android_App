package first.android.cis.network

import first.android.cis.network.api.NewsApi
import first.android.cis.network.api.UsersApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://192.168.100.7:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val newsApi: NewsApi =
        retrofit.create(NewsApi::class.java)

    val usersApi: UsersApi =
        retrofit.create(UsersApi::class.java)
}