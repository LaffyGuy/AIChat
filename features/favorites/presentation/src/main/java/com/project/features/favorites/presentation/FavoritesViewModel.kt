package com.project.features.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.features.favorites.domain.DeleteChatFromFavoritesUseCase
import com.project.features.favorites.domain.GetAllFavoritesChatsUseCase
import com.project.features.favorites.domain.GetFavoriteSearchChatsUseCase
import com.project.features.favorites.domain.entities.FavoriteChatSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    getAllFavoritesChatsUseCase: GetAllFavoritesChatsUseCase,
    private val deleteChatFromFavoritesUseCase: DeleteChatFromFavoritesUseCase,
    private val getFavoriteSearchChatsUseCase: GetFavoriteSearchChatsUseCase
) : ViewModel() {


//    val loadResultFlow: StateFlow<LoadResult<FavoritesChatsUiState>> =
//         getAllFavoritesChatsUseCase().map { loadResult ->
//             when(loadResult) {
//                 LoadResult.Loading -> LoadResult.Loading
//                 is LoadResult.Success -> LoadResult.Success(FavoritesChatsUiState(loadResult.data))
//                 is LoadResult.Error -> LoadResult.Error(loadResult.exception)
//             }
//
//         }.stateIn(
//             scope = viewModelScope,
//             started = SharingStarted.WhileSubscribed(1000),
//             initialValue = LoadResult.Loading
//         )
//

//    }

    private val _searchFieldValue = MutableStateFlow("")
    val searchFieldValue: StateFlow<String> = _searchFieldValue


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val favoriteChatUiState: StateFlow<FavoritesChatsUiState> = _searchFieldValue
        .debounce { query ->
            if (query.isBlank()) 0L else 300L
        }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) getAllFavoritesChatsUseCase() else getFavoriteSearchChatsUseCase(
                query
            )
        }
        .map { result ->
            FavoritesChatsUiState(
                loadResult = result,
                searchValue = _searchFieldValue.value
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = FavoritesChatsUiState()
        )


    fun updateSearchValue(query: String) {
        _searchFieldValue.value = query
    }

    fun deleteFromFavorites(chatId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            deleteChatFromFavoritesUseCase(chatId, isFavorite)
        }
    }

}


data class FavoritesChatsUiState(
    val loadResult: LoadResult<List<FavoriteChatSession>> = LoadResult.Loading,
    val searchValue: String = "",
)