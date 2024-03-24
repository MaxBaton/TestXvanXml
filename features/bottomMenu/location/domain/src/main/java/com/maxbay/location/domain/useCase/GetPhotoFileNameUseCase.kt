package com.maxbay.location.domain.useCase

import java.text.SimpleDateFormat
import java.util.Calendar

class GetPhotoFileNameUseCase(
    private val calendar: Calendar,
    private val simpleDateFormat: SimpleDateFormat
) {
    fun execute(additionalSecond: Int): String {
        calendar.time = Calendar.getInstance().time
        calendar.add(Calendar.SECOND, additionalSecond)
        return simpleDateFormat.format(calendar.time)
    }
}