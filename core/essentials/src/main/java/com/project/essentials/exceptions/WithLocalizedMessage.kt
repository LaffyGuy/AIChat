package com.project.essentials.exceptions

import com.project.essentials.resources.StringProviderStore

interface WithLocalizedMessage {

    fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String

}