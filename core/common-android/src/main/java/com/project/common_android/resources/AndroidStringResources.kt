package com.project.common_android.resources

import android.content.Context
import com.project.essentials.resources.StringResources
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidStringResources @Inject constructor(
    @ApplicationContext private val context: Context
): StringResources {

    override fun getString(resId: Int, vararg args: Any?): String {
        return context.getString(resId, *args)
    }
}