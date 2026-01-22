package com.project.features.favorites.domain

import com.project.essentials.LoadResult
import com.project.features.favorites.domain.entities.FavoriteChatSession
import kotlinx.coroutines.flow.Flow

interface GetAllFavoritesChatsUseCase {

    operator fun invoke(): Flow<LoadResult<List<FavoriteChatSession>>>

}