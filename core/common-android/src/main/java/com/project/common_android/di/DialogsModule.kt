package com.project.common_android.di

import com.project.common_android.dialogs.DialogsImpl
import com.project.essentials.dialogs.Dialogs
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface DialogsModule {

    @Binds
    @ActivityRetainedScoped
    fun bindDialogs(
        impl: DialogsImpl
    ): Dialogs

}