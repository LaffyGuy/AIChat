package com.project.features.chats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.features.chats.domain.DeleteChatUseCase
import com.project.features.chats.domain.GetChatsUseCase
import com.project.features.chats.domain.entities.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
    private val deleteChatUseCase: DeleteChatUseCase
): ViewModel() {


    val chatsFlow: StateFlow<LoadResult<ChatsUiState>> =
        getChatsUseCase().map { result ->
              when(result) {
                  LoadResult.Loading -> LoadResult.Loading
                  is LoadResult.Success -> LoadResult.Success(ChatsUiState(result.data))
                  is LoadResult.Error -> LoadResult.Error(result.exception)
              }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = LoadResult.Loading
            )

}

data class ChatsUiState(
    val data: List<Chat>
)