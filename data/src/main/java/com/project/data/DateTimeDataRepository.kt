package com.project.data

import java.time.ZonedDateTime

interface DateTimeDataRepository {

    fun now(): ZonedDateTime

}