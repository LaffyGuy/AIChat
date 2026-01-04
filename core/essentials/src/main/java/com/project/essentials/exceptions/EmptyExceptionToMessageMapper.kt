package com.project.essentials.exceptions

class EmptyExceptionToMessageMapper : ExceptionToMessageMapper {

    override fun getLocalizedMessage(exception: Exception): String {
        return exception.message ?: "Ann error occurred"
    }
}