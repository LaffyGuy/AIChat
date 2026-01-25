package com.project.features.main.domain.resources

import com.project.essentials.resources.StringProvider

interface MainStringProvider: StringProvider {

    val quotaExceededMessage: String

    val safetyBlockedMessage: String

    val serverOverloadedMessage: String

    val promptInvalidMessage: String

    val unknownErrorMessage: String

    val emptyPromptErrorMessage: String

}