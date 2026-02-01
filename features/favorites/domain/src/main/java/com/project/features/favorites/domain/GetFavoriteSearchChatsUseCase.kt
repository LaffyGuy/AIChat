package com.project.features.favorites.domain

import com.project.essentials.LoadResult
import com.project.features.favorites.domain.entities.FavoriteChatSession
import kotlinx.coroutines.flow.Flow

interface GetFavoriteSearchChatsUseCase {

    operator fun invoke(searchQuery: String): Flow<LoadResult<List<FavoriteChatSession>>>

}