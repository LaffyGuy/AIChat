package com.project.features.main.presentation.exceptions

import com.project.essentials.exceptions.ExceptionToMessageMapper
import com.project.essentials.exceptions.WithLocalizedMessage
import com.project.essentials.resources.StringProviderStore
import com.project.features.main.domain.resources.MainStringProvider
import javax.inject.Inject

//class MainExceptionToMessageMapper @Inject constructor(
//    private val stringProviderStore: StringProviderStore
//): ExceptionToMessageMapper {
//    override fun getLocalizedMessage(exception: Exception): String {
//        return if(exception is WithLocalizedMessage) {
//            exception.getLocalizedErrorMessage(stringProviderStore)
//        } else {
//            stringProviderStore<MainStringProvider>().unknownErrorMessage
//        }
//    }
//}