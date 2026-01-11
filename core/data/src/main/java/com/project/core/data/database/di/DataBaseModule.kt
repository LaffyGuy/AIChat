package com.project.core.data.database.di

import android.content.Context
import androidx.room.Room
import com.project.core.data.database.ChatDataBase
import com.project.core.data.database.dao.PromptSampleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context
    ): ChatDataBase {
        return Room.databaseBuilder(
            context,
            ChatDataBase::class.java,
            "chat.db"
        ).createFromAsset("promptsamplecgatdb.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePromptSampleDao(chatDb: ChatDataBase): PromptSampleDao {
        return chatDb.getPromptSampleDao()
    }

}