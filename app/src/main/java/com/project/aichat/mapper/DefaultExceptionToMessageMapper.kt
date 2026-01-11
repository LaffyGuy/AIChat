package com.project.aichat.mapper

import android.content.Context
import com.project.aichat.R
import com.project.essentials.exceptions.ConnectionException
import com.project.essentials.exceptions.ExceptionToMessageMapper
import com.project.essentials.exceptions.UnknownException
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultExceptionToMessageMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : ExceptionToMessageMapper {

    override fun getLocalizedMessage(exception: Exception): String {
        return when (exception) {
            is UnknownException -> context.getString(R.string.app_unknown_error_message)
            is ConnectionException -> context.getString(R.string.app_connection_error_message)
            else -> context.getString(R.string.app_unknown_error_message)
        }
    }
}