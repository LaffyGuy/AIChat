package com.project.glue.init

import com.project.data.DateTimeDataRepository
import com.project.features.init.domain.repositories.DateTimeRepository
import java.time.ZonedDateTime
import javax.inject.Inject

class InitDateTimeRepository @Inject constructor(private val dateTimeDataRepository: DateTimeDataRepository): DateTimeRepository {

    override fun now(): ZonedDateTime {
        return dateTimeDataRepository.now()
    }
}