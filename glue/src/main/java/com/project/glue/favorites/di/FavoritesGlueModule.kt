package com.project.glue.favorites.di

import com.project.features.favorites.domain.repositories.FavoriteChatsRepository
import com.project.glue.favorites.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesGlueModule {

    @Binds
    fun bindFavoritesChatsRepository(
        impl: FavoritesRepository
    ): FavoriteChatsRepository

}