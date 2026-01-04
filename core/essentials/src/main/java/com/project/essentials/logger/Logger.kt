package com.project.essentials.logger

interface Logger {

    fun d(message: String)

    fun e(exception: Exception, message: String = "Error!")

    companion object : Logger {

        private var instance: Logger = DefaultLogger()

        override fun d(message: String) {
            instance.d(message)

        }

        override fun e(exception: Exception, message: String) {
            instance.e(exception, message)
            exception.printStackTrace()
        }

        fun set(logger: Logger) {
            this.instance = logger
        }

        fun reset() {
            instance = DefaultLogger()
        }

    }

}
