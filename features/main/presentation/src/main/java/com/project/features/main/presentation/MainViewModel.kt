package com.project.features.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.essentials.entities.MessageAuthor
import com.project.essentials.exceptions.ConnectionException
import com.project.features.main.domain.GetAIChatResponseUseCase
import com.project.features.main.domain.GetChatHistoryUseCase
import com.project.features.main.domain.SaveMessageUseCase
import com.project.features.main.domain.SaveNewChatUseCase
import com.project.features.main.domain.entities.MainChatSession
import com.project.features.main.presentation.mappers.toDomain
import com.project.navigation.common.routes.ChatRoute
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@HiltViewModel(assistedFactory = MainViewModel.Factory::class)
class MainViewModel @AssistedInject constructor(
    private val getAIChatResponseUseCase: GetAIChatResponseUseCase,
    private val saveNewChatUseCase: SaveNewChatUseCase,
    private val getChatHistoryUseCase: GetChatHistoryUseCase,
    private val saveMessageUseCase: SaveMessageUseCase,
    @Assisted navKey: ChatRoute
): ViewModel() {

    private val _chatSessionId = MutableStateFlow(
        if (navKey.chatId != -1L) navKey.chatId else null
    )

    private val _inputState = MutableStateFlow(TextInputUiState())

    private val _messages = MutableStateFlow<List<ChatMessageUiState>>(emptyList())

    val _aiResponse = MutableStateFlow<LoadResult<String>>(LoadResult.Loading)


    init {
        _chatSessionId.value?.let { id ->
            loadChatHistory(id)
        }
    }

    val uiState = combine(
        _inputState,
        _messages,
        _aiResponse
    ) { input, messages, response ->
        MainUiState(
            textInputState = input,
            messages = messages,
            shouldShowWelcomeItem = messages.isEmpty()

        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = MainUiState()
    )

    fun onTextChanged(input: String) {
        _inputState.update { it.copy(text = input) }
    }

    private fun loadChatHistory(chatId: Long) {
        viewModelScope.launch {
            getChatHistoryUseCase(chatId).collect { history ->
                _messages.update { history.map { it.toUiState() } }
            }
        }
    }

    fun generateAIResponse(prompt: String) {

        if (prompt.isBlank()) {
            _inputState.update { it.copy(isError = true, errorMessage = "Prompt cannot be empty") }
            return
        }

        val currentChatId = _chatSessionId.value

        val userMessage = ChatMessageUiState(
            id = 0,
            text = prompt,
            author = MessageAuthor.USER
        )

        val loadingAiMessage = ChatMessageUiState(
            id = 0,
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

            val id = if (currentChatId == null) {
                val newId = saveNewChatUseCase(
                    MainChatSession(
                        id =0L,
                        title = prompt.take(20),
                        isFavorite = false,
                        createdAt = LocalDate.now()
                    )
                )
                _chatSessionId.value = newId
                newId
            } else currentChatId

            saveMessageUseCase(id, prompt, MessageAuthor.USER)

            val historyForAi = _messages.value
                .filter { !it.isLoading && !it.isError }
                .map { it.toDomain() }

            try {
                getAIChatResponseUseCase(historyForAi, prompt)
                    .collect { response ->
                        _messages.update { list ->
                            list.map {
                                if (it.isLoading && it.author == MessageAuthor.AI) {
                                    it.copy(text = response, isLoading = false)
                                } else it
                            }
                        }
                        saveMessageUseCase(id, response, MessageAuthor.AI)
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

    @AssistedFactory
    interface Factory {
        fun create(navKey: ChatRoute): MainViewModel
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

