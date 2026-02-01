package com.project.features.favorites.domain.usecases

import com.project.essentials.LoadResult
import com.project.features.favorites.domain.GetFavoriteSearchChatsUseCase
import com.project.features.favorites.domain.entities.FavoriteChatSession
import com.project.features.favorites.domain.repositories.FavoriteChatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteSearchChatsUseCaseImpl @Inject constructor(
    private val favoriteChatsRepository: FavoriteChatsRepository
): GetFavoriteSearchChatsUseCase {

    override fun invoke(searchQuery: String): Flow<LoadResult<List<FavoriteChatSession>>> {
        return favoriteChatsRepository.getFavoriteSearchChats(searchQuery)
    }

}