package com.project.glue.favorites

import com.project.data.ChatsDataRepository
import com.project.essentials.LoadResult
import com.project.features.favorites.domain.entities.FavoriteChatSession
import com.project.features.favorites.domain.repositories.FavoriteChatsRepository
import com.project.glue.favorites.mappers.toFavoriteChatSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val favoritesChatsDataRepository: ChatsDataRepository): FavoriteChatsRepository {

    override fun getAllFavoritesChats(): Flow<LoadResult<List<FavoriteChatSession>>> {
        return favoritesChatsDataRepository.getAllFavoritesChats().map { loadResult ->
            when(loadResult) {
                LoadResult.Loading -> LoadResult.Loading
                is LoadResult.Success -> LoadResult.Success(loadResult.data.map { it.toFavoriteChatSession() })
                is LoadResult.Error -> LoadResult.Error(loadResult.exception)
            }
        }
    }

    override suspend fun deleteChatFromFavorites(chatId: Long, isFavrite: Boolean) {
        favoritesChatsDataRepository.updateFavoriteStatus(chatId, isFavrite)
    }
}