package com.project.glue.chats.di

import com.project.features.chats.domain.repositories.ChatsRepository
import com.project.glue.chats.ChatsGlueRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ChatsModule {

    @Binds
    fun bindChatsRepository(
        impl: ChatsGlueRepository
    ): ChatsRepository

}