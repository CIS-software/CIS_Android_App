package com.cis.domain.models.calendar

import com.google.gson.annotations.SerializedName

data class CalendarItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("date")
    val dayOfWeek: String,

    @SerializedName("description")
    val event: String
)
