package com.project.features.chats.presentation.di

import com.project.features.chats.domain.GetChatsUseCase
import com.project.features.chats.domain.usecases.DeleteChatUseCaseImpl
import com.project.features.chats.domain.usecases.GetChatsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ChatsModule {

    @Binds
    fun bindGetChatsUseCase(
        impl: GetChatsUseCaseImpl
    ): GetChatsUseCase

    @Binds
    fun bindDeleteChatUseCase(
        impl: DeleteChatUseCaseImpl
    ): DeleteChatUseCaseImpl

}