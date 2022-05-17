package com.cis.domain.repository

import com.cis.domain.models.calendar.CalendarIdAndAccess
import com.cis.domain.models.calendar.CalendarList
import retrofit2.Response

interface CalendarRepository {
    suspend fun getCalendar(accessToken: String): Response<CalendarList>

    suspend fun deleteEvent(calendarIdAndAccess: CalendarIdAndAccess): Response<Throwable>
}