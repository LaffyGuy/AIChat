package com.project.common_android.handler

import android.content.Context
import android.widget.Toast
import com.project.essentials.exceptions.ExceptionToMessageMapper
import com.project.essentials.handler.ExceptionHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidExceptionHandler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val exceptionToMessageMapper: ExceptionToMessageMapper
): ExceptionHandler {

    override fun handleException(exception: Exception) {
        val message = exceptionToMessageMapper.getLocalizedMessage(exception)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}