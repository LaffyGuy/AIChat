package com.project.data.theme.di

import com.project.data.ThemeDataRepository
import com.project.data.theme.ThemeDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ThemeDataModule {

    @Binds
    fun bindThemeDataRepository(
        impl: ThemeDataRepositoryImpl
    ): ThemeDataRepository

}