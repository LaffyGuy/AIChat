package com.project.features.chats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
//import com.project.features.chats.domain.DeleteChatUseCase
import com.project.features.chats.domain.GetChatsUseCase
import com.project.features.chats.domain.GetSearchChatsUseCase
import com.project.features.chats.domain.UpdateFavoriteStatusUseCase
import com.project.features.chats.domain.entities.ChatSession
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
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    getChatsUseCase: GetChatsUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase,
    private val searchChatsUseCase: GetSearchChatsUseCase,
//    private val deleteChatUseCase: DeleteChatUseCase
) : ViewModel() {

    private val _searchFieldValue = MutableStateFlow("")
    val searchFieldValue: StateFlow<String> = _searchFieldValue

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val chatUiState: StateFlow<ChatsUiState> = _searchFieldValue
        .debounce { query -> if (query.isBlank()) 0L else 300L }
        .distinctUntilChanged { old, new -> old.trim() == new.trim() }
        .flatMapLatest { query ->
            val sanitizedQuery = query.trim()
            if (sanitizedQuery.isEmpty()) getChatsUseCase() else searchChatsUseCase(sanitizedQuery)
        }
        .scan(ChatsUiState()) { previousState, result ->

            if (result is LoadResult.Loading && previousState.loadResult is LoadResult.Success) {
                previousState
            } else {
                ChatsUiState(loadResult = result)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChatsUiState(loadResult = LoadResult.Loading)
        )


    fun updateSearchValue(searchQuery: String) {
        _searchFieldValue.value = searchQuery
    }

    fun updateFavoriteStatus(chatId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            updateFavoriteStatusUseCase(chatId, isFavorite)
        }
    }

}

data class ChatsUiState(
    val loadResult: LoadResult<List<ChatSession>> = LoadResult.Loading,
    val searchValue: String = "",
)