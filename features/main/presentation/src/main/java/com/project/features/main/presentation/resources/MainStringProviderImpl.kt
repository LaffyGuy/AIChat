package com.project.features.main.presentation.resources

import android.content.Context
import com.project.features.main.domain.resources.MainStringProvider
import com.project.features.main.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MainStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
): MainStringProvider {

    override val quotaExceededMessage = context.getString(R.string.quota_exceeded_error_message)

    override val safetyBlockedMessage = context.getString(R.string.safety_blocked_error_message)

    override val serverOverloadedMessage = context.getString(R.string.server_overloaded_error_message)

    override val promptInvalidMessage = context.getString(R.string.prompt_invalid_error_message)

    override val unknownErrorMessage = context.getString(R.string.unknown_error_message)

    override val emptyPromptErrorMessage = context.getString(R.string.empty_prompt_error_message)
}