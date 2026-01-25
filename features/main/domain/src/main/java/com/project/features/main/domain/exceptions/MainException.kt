package com.project.features.main.domain.exceptions

import com.project.essentials.exceptions.AppException
import com.project.essentials.exceptions.WithLocalizedMessage
import com.project.essentials.resources.StringProviderStore
import com.project.features.main.domain.resources.MainStringProvider

abstract class MainException(
    message: String,
    cause: Throwable? = null
): AppException(message, cause), WithLocalizedMessage {

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<MainStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: MainStringProvider): String

}

class QuotaExceededException: MainException("Quota exceeded") {
    override fun getLocalizedErrorMessage(stringProvider: MainStringProvider): String {
        return stringProvider.quotaExceededMessage
    }
}

class SafetyException: MainException("Blocked by safety filters") {
    override fun getLocalizedErrorMessage(stringProvider: MainStringProvider): String {
        return stringProvider.safetyBlockedMessage
    }
}

class ServerOverloadedException: MainException("Gemini server error") {
    override fun getLocalizedErrorMessage(stringProvider: MainStringProvider): String {
        return stringProvider.serverOverloadedMessage
    }
}

class PromptInvalidException: MainException("Invalid prompt") {
    override fun getLocalizedErrorMessage(stringProvider: MainStringProvider): String {
        return stringProvider.promptInvalidMessage
    }
}
