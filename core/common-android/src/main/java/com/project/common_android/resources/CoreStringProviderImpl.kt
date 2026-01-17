package com.project.common_android.resources

import android.content.Context
import com.project.common_android.R
import com.project.essentials.resources.CoreStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CoreStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
): CoreStringProvider {

    override val unknownErrorMessage = context.getString(R.string.app_unknown_error_message)
    override val connectionErrorMessage = context.getString(R.string.app_connection_error_message)

    override val deleteAction = context.getString(R.string.delete)
    override val cancelAction = context.getString(R.string.cancel)
}