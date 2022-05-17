package com.example.data.network.api

import com.cis.domain.models.calendar.CalendarList
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header

interface CalendarApi {
    @GET("/get-calendar")
    suspend fun getCalendar(@Header("Authorization") accessToken: String): Response<CalendarList>
    @DELETE
    suspend fun deleteEvent(@Header("Authorization") accessToken: String): Response<Throwable>
}