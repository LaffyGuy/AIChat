package com.project.features.favorites.presentation.di

import com.project.features.favorites.domain.DeleteChatFromFavoritesUseCase
import com.project.features.favorites.domain.GetAllFavoritesChatsUseCase
import com.project.features.favorites.domain.GetFavoriteSearchChatsUseCase
import com.project.features.favorites.domain.usecases.DeleteChatFromFavoritesUseCaseImpl
import com.project.features.favorites.domain.usecases.GetAllFavoriteChatsUseCaseImpl
import com.project.features.favorites.domain.usecases.GetFavoriteSearchChatsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesModule {

    @Binds
    fun bindGetAllFavoritesChatsUseCase(
        impl: GetAllFavoriteChatsUseCaseImpl
    ): GetAllFavoritesChatsUseCase

    @Binds
    fun bindDeleteChatsFromFavoritesUseCase(
        impl: DeleteChatFromFavoritesUseCaseImpl
    ): DeleteChatFromFavoritesUseCase

    @Binds
    fun bindGetFavoriteSearchChatsUseCase(
        impl: GetFavoriteSearchChatsUseCaseImpl
    ): GetFavoriteSearchChatsUseCase

}