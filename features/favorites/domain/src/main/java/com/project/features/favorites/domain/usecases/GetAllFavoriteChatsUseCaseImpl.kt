package com.project.features.favorites.domain.usecases

import com.project.essentials.LoadResult
import com.project.features.favorites.domain.GetAllFavoritesChatsUseCase
import com.project.features.favorites.domain.entities.FavoriteChatSession
import com.project.features.favorites.domain.repositories.FavoriteChatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteChatsUseCaseImpl @Inject constructor(
    private val favoriteChatsRepository: FavoriteChatsRepository
): GetAllFavoritesChatsUseCase {

    override fun invoke(): Flow<LoadResult<List<FavoriteChatSession>>> {
        return favoriteChatsRepository.getAllFavoritesChats()
    }

}