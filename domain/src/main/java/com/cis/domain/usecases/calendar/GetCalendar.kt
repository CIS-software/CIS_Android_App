package com.cis.domain.usecases.calendar

import com.cis.domain.models.calendar.CalendarList
import com.cis.domain.repository.CalendarRepository
import retrofit2.Response

class GetCalendar(private val calendarRepository: CalendarRepository) {
    suspend fun execute(accessToken: String): Response<CalendarList>{
        return calendarRepository.getCalendar(accessToken = accessToken)
    }
}