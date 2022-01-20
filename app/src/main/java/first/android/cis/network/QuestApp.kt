package first.android.cis.network

import android.app.Application
import first.android.cis.network.newsAPI.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class QuestApp: Application() {


    lateinit var newsApi: NewsApi

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
    }

    private fun configureRetrofit(){
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8000")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())//26:15 таймкод
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        newsApi = retrofit.create(NewsApi::class.java)
    }


}