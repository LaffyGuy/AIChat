package com.project.data.chats.di

import com.project.data.ChatsDataRepository
import com.project.data.chats.ChatsDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ChatsModule {

    @Binds
    fun bindChatsDataRepository(
        impl: ChatsDataRepositoryImpl
    ): ChatsDataRepository

}