package com.project.common_android.di

import com.project.common_android.dialogs.DialogsImpl
import com.project.essentials.dialogs.Dialogs
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface DialogsModule {

    @Binds
    fun bindDialogs(
        impl: DialogsImpl
    ): Dialogs

}