package com.example.data.calendar

import com.cis.domain.models.calendar.CalendarIdAndAccess
import com.cis.domain.models.calendar.CalendarList
import com.cis.domain.repository.CalendarRepository
import com.example.data.network.Retrofit
import retrofit2.Response

class CalendarRepositoryImpl: CalendarRepository {
    override suspend fun getCalendar(accessToken: String): Response<CalendarList> {
        return Retrofit.calendarApi.getCalendar(accessToken = accessToken)
    }

    override suspend fun deleteEvent(calendarIdAndAccess: CalendarIdAndAccess): Response<Throwable> {
        return Retrofit.calendarApi.deleteEvent(id = calendarIdAndAccess.id, accessToken = calendarIdAndAccess.accessToken)
    }
}