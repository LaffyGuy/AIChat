package com.project.features.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.features.main.domain.GetAIChatResponseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAIChatResponseUseCase: GetAIChatResponseUseCase
): ViewModel() {

    private val _inputState = MutableStateFlow(TextInputUiState())
    val inputState: StateFlow<TextInputUiState> = _inputState

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages


    val _aiResponse = MutableStateFlow<LoadResult<String>>(LoadResult.Loading)
    val aiResponse: StateFlow<LoadResult<String>> = _aiResponse

    val uiState = combine(
        _inputState,
        _messages,
        _aiResponse
    ) { input, messages, response ->
        MainUiState(
            textInputState = input,
            messages = messages,
            responseStatus = response,
            shouldShowWelcomeItem = response !is LoadResult.Success && response !is  LoadResult.Error
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = MainUiState()
    )

    fun onTextChanged(input: String) {
        _inputState.update { it.copy(text = input) }
    }

    fun generateAIResponse(prompt: String) {

        if (prompt.isBlank()) {
            _inputState.update { it.copy(isError = true, errorMessage = "Prompt cannot be empty") }
            return
        }

        val userMessage = ChatMessage(
            text = prompt,
            author = MessageAuthor.USER
        )

        _messages.update { it + userMessage }

        _inputState.update {
            it.copy(
                text = "",
                isEnabled = false,
                isTrailingIconEnabled = false,
                isError = false
            )
        }

        _aiResponse.value = LoadResult.Loading

        viewModelScope.launch {
            try {
                val response = getAIChatResponseUseCase(prompt)

                val aiMessage = ChatMessage(
                    text = response,
                    author = MessageAuthor.AI
                )

                _messages.update { it + aiMessage }
                _aiResponse.value = LoadResult.Success(response)
            } catch (e: Exception) {
                _aiResponse.value = LoadResult.Error(e)
            } finally {
                _inputState.update {
                    it.copy(
                        isEnabled = true,
                        isTrailingIconEnabled = true
                    )
                }
            }
        }
    }

}

data class MainUiState(
    val textInputState: TextInputUiState = TextInputUiState(),
    val messages: List<ChatMessage> = emptyList(),
    val responseStatus: LoadResult<String> = LoadResult.Loading,
    val shouldShowWelcomeItem: Boolean = true
)

data class TextInputUiState(
    val text: String = "",
    val isEnabled: Boolean = true,
    val isTrailingIconEnabled: Boolean = true,
    val isError: Boolean = false,
    val errorMessage: String? = null,
)

enum class MessageAuthor {
    USER,
    AI
}

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val author: MessageAuthor,
    val timestamp: Long = System.currentTimeMillis()
)