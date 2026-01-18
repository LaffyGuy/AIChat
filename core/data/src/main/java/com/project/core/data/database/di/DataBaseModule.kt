package com.project.core.data.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.project.core.data.database.ChatDataBase
import com.project.core.data.database.dao.AIChatDao
import com.project.core.data.database.dao.PromptSampleDao
import com.project.core.data.database.seed.PromptSampleSeed
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Provider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context,
        promptSampleDao: Provider<PromptSampleDao>
    ): ChatDataBase {
        return Room.databaseBuilder(
            context,
            ChatDataBase::class.java,
            "chat.db"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                CoroutineScope(Dispatchers.IO).launch {
                    val dao = promptSampleDao.get()
                    if(dao.count() == 0) {
                        dao.insertAll(PromptSampleSeed.getSamples())
                    }

                }
            }

        })
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePromptSampleDao(chatDb: ChatDataBase): PromptSampleDao {
        return chatDb.getPromptSampleDao()
    }

    @Provides
    @Singleton
    fun provideAIChatDao(chatDb: ChatDataBase): AIChatDao {
        return chatDb.getAIChatDao()
    }

}