package com.project.features.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.essentials.exceptions.ConnectionException
import com.project.features.main.domain.GetAIChatResponseUseCase
//import com.project.features.main.domain.GetRecipeAIResponseUseCase
import com.project.essentials.entities.MessageAuthor
import com.project.features.main.domain.SaveNewChatUseCase
import com.project.features.main.domain.entities.ChatSession
import com.project.features.main.presentation.mappers.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAIChatResponseUseCase: GetAIChatResponseUseCase,
    private val saveNewChatUseCase: SaveNewChatUseCase
): ViewModel() {

    private val _inputState = MutableStateFlow(TextInputUiState())

    private val _messages = MutableStateFlow<List<ChatMessageUiState>>(emptyList())

    val _aiResponse = MutableStateFlow<LoadResult<String>>(LoadResult.Loading)

    private val _chatSessionId = MutableStateFlow<Long?>(null)


    val uiState = combine(
        _inputState,
        _messages,
        _aiResponse
    ) { input, messages, response ->
        MainUiState(
            textInputState = input,
            messages = messages,
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

        val history = _messages.value
            .filter { !it.isLoading && !it.isError }
            .map { it.toDomain() }

        val userMessage = ChatMessageUiState(
            text = prompt,
            author = MessageAuthor.USER
        )

        val loadingAiMessage = ChatMessageUiState(
            text = "",
            author = MessageAuthor.AI,
            isLoading = true
        )

        _messages.update { it + userMessage + loadingAiMessage }

        _inputState.update {
            it.copy(
                text = "",
                isEnabled = false,
                isTrailingIconEnabled = false,
                isError = false
            )
        }

        viewModelScope.launch {

            if (_chatSessionId.value == null) {
                val chatSession = ChatSession(
                    id = 0L,
                    title = prompt.take(20)
                )
                saveNewChatUseCase(chatSession)
            }

            try {
                getAIChatResponseUseCase(history, prompt)
                    .collect { response ->
                        _messages.update { list ->
                            list.map {
                                if (it.isLoading && it.author == MessageAuthor.AI) {
                                    it.copy(text = response, isLoading = false)
                                } else it
                            }
                        }
                    }

            } catch (e: ConnectionException) {
                _messages.update { list ->
                    list.map {
                        if (it.isLoading && it.author == MessageAuthor.AI) {
                            it.copy(
                                isError = true,
                                errorText = e.message,
                                isLoading = false
                            )
                        } else it
                    }
                }
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
    val messages: List<ChatMessageUiState> = emptyList(),
    val shouldShowWelcomeItem: Boolean = true,
    val readyPromptsList: List<ReadyPrompt> = readyPrompts,
    val isMessageGenerated: Boolean = false
)

data class TextInputUiState(
    val text: String = "",
    val hint: String = "Write your request",
    val isEnabled: Boolean = true,
    val isTrailingIconEnabled: Boolean = true,
    val isError: Boolean = false,
    val errorMessage: String? = null,
)

