package com.project.data.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.project.data.datastore.appDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDataStoreModule {

    @Provides
    fun provideAppDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.appDataStore
    }
}