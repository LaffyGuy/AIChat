package com.project.features.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.features.favorites.domain.DeleteChatFromFavoritesUseCase
import com.project.features.favorites.domain.GetAllFavoritesChatsUseCase
import com.project.features.favorites.domain.entities.FavoriteChatSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoritesChatsUseCase: GetAllFavoritesChatsUseCase,
    private val deleteChatFromFavoritesUseCase: DeleteChatFromFavoritesUseCase
): ViewModel() {


    val loadResultFlow: StateFlow<LoadResult<FavoritesChatsUiState>> =
         getAllFavoritesChatsUseCase().map { loadResult ->
             when(loadResult) {
                 LoadResult.Loading -> LoadResult.Loading
                 is LoadResult.Success -> LoadResult.Success(FavoritesChatsUiState(loadResult.data))
                 is LoadResult.Error -> LoadResult.Error(loadResult.exception)
             }

         }.stateIn(
             scope = viewModelScope,
             started = SharingStarted.WhileSubscribed(1000),
             initialValue = LoadResult.Loading
         )

    fun deleteFromFavorites(chatId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
              deleteChatFromFavoritesUseCase(chatId, isFavorite)
        }
    }

}


data class FavoritesChatsUiState(
    val data: List<FavoriteChatSession>
)