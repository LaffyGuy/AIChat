package com.project.features.init.domain.repositories

import java.time.ZonedDateTime

interface DateTimeRepository {

    fun now(): ZonedDateTime

}