package com.project.essentials.resources

interface StringResources {

    fun getString(
        resId: Int,
        vararg args: Any?
    ): String

}