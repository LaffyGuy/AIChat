package com.project.essentials.exceptions

abstract class AppException(
    message: String,
    cause: Throwable? = null
): Exception(message, cause)

class UnknownException: AppException("Unknown exception")

class ConnectionException(
    cause: Throwable? = null
): AppException("Network error", cause)