package com.project.essentials.exceptions

import com.project.essentials.resources.CoreStringProvider
import com.project.essentials.resources.StringProviderStore

abstract class AppException(
    message: String,
    cause: Throwable? = null
): Exception(message, cause)

abstract class CoreAppException(
    message: String,
    cause: Throwable? = null
): AppException(message, cause), WithLocalizedMessage {
    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<CoreStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String

}

class UnknownException: CoreAppException("Unknown exception") {
    override fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String {
        return stringProvider.unknownErrorMessage
    }
}

class ConnectionException(
    cause: Throwable? = null
): CoreAppException("Network error", cause) {
    override fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String {
        return stringProvider.connectionErrorMessage
    }
}