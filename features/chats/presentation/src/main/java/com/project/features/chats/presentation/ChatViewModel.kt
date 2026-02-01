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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    getChatsUseCase: GetChatsUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase,
    private val searchChatsUseCase: GetSearchChatsUseCase
//    private val deleteChatUseCase: DeleteChatUseCase
): ViewModel() {

//    val chatsFlow: StateFlow<LoadResult<ChatsUiState>> =
//        getChatsUseCase().map { result ->
//              when(result) {
//                  LoadResult.Loading -> LoadResult.Loading
//                  is LoadResult.Success -> LoadResult.Success(ChatsUiState(result.data))
//                  is LoadResult.Error -> LoadResult.Error(result.exception)
//              }
//        }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(1000),
//                initialValue = LoadResult.Loading
//            )

    private val _chatsUiState = MutableStateFlow(ChatsUiState())
    val chatsUiState: StateFlow<ChatsUiState> = _chatsUiState

    init {
        viewModelScope.launch {
            getChatsUseCase().collect { result ->
                _chatsUiState.update { it.copy(data = result) }
            }
        }
    }

    fun updateFavoriteStatus(chatId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            updateFavoriteStatusUseCase(chatId, isFavorite)
        }
    }

    fun updateSearchValue(searchQuery: String) {
        _chatsUiState.update { it.copy(searchValue = searchQuery) }
    }

}

data class ChatsUiState(
    val data: LoadResult<List<ChatSession>> = LoadResult.Loading,
    val searchValue: String = ""
)