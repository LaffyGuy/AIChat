package com.project.essentials.exceptions.mapper

import com.project.essentials.exceptions.ExceptionToMessageMapper
import com.project.essentials.exceptions.WithLocalizedMessage
import com.project.essentials.resources.CoreStringProvider
import com.project.essentials.resources.StringProviderStore
import javax.inject.Inject

class DefaultExceptionToMessageMapper @Inject constructor(
    private val stringProviderStore: StringProviderStore
): ExceptionToMessageMapper {

    override fun getLocalizedMessage(exception: Exception): String {
        return if (exception is WithLocalizedMessage) {
            exception.getLocalizedErrorMessage(stringProviderStore)
        } else {
            stringProviderStore<CoreStringProvider>().unknownErrorMessage
        }
    }
}