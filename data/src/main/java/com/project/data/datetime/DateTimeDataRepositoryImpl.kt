package com.project.data.datetime

import com.project.data.DateTimeDataRepository
import java.time.ZonedDateTime
import javax.inject.Inject

class DateTimeDataRepositoryImpl @Inject constructor(): DateTimeDataRepository {

    override fun now(): ZonedDateTime {
        return ZonedDateTime.now()
    }
}