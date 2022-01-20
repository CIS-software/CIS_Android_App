package first.android.cis.network

import first.android.cis.network.newsAPI.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    //private val httpLoggingInterceptor = HttpLoggingInterceptor()
    //httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


    private val okHttpClient = OkHttpClient.Builder()
        //.addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8000")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())//26:15 таймкод
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val newsApi: NewsApi by lazy{
        retrofit.create(NewsApi::class.java)
    }
}