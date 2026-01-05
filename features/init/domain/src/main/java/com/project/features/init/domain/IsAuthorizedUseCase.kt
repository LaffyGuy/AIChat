package com.project.features.init.domain

import com.project.essentials.exceptions.ConnectionException
import kotlinx.coroutines.delay
import javax.inject.Inject

class IsAuthorizedUseCase @Inject constructor(){


    suspend operator fun invoke(): Boolean {
        delay(2000)
        throw ConnectionException()
    }
}