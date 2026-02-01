package com.project.features.favorites.domain.repositories

import com.project.essentials.LoadResult
import com.project.features.favorites.domain.entities.FavoriteChatSession
import kotlinx.coroutines.flow.Flow

interface FavoriteChatsRepository {

    fun getAllFavoritesChats(): Flow<LoadResult<List<FavoriteChatSession>>>

    suspend fun deleteChatFromFavorites(chatId: Long, isFavorite: Boolean)

    fun getFavoriteSearchChats(searchQuery: String): Flow<LoadResult<List<FavoriteChatSession>>>

}