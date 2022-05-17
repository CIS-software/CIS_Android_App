package com.example.data.network.api

import com.cis.domain.models.calendar.CalendarList
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CalendarApi {
    @GET("/get-calendar")
    suspend fun getCalendar(@Header("Authorization") accessToken: String): Response<CalendarList>
    @DELETE("/training/{id}")
    suspend fun deleteEvent(@Path("id")id: Int, @Header("Authorization") accessToken: String): Response<Throwable>
}