package com.cis.domain.models.calendar

data class Calendar(val week: Array<String> = arrayOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"), val event: String)
//Закончил на 10:00