package com.example.data.network

import com.example.data.network.api.CalendarApi
import com.example.data.network.api.NewsApi
import com.example.data.network.api.UsersApi
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

    val calendarApi: CalendarApi =
        retrofit.create(CalendarApi::class.java)
}