package com.project.essentials.logger

class DefaultLogger: Logger {

    override fun d(message: String) {
        println("DEBUG: $message")
    }

    override fun e(exception: Exception, message: String) {
        println("ERROR: $message")
        exception.printStackTrace()
    }

}