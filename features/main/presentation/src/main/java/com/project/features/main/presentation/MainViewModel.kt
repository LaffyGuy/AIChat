package com.project.features.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.entities.MessageAuthor
import com.project.essentials.exceptions.ConnectionException
import com.project.features.main.domain.GetAIChatResponseUseCase
import com.project.features.main.domain.GetChatHistoryUseCase
import com.project.features.main.domain.SaveMessageUseCase
import com.project.features.main.domain.SaveNewChatUseCase
import com.project.features.main.domain.entities.ChatMessage
import com.project.features.main.domain.entities.MainChatSession
import com.project.features.main.domain.resources.MainStringProvider
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

@HiltViewModel(assistedFactory = MainViewModel.Factory::class)
class MainViewModel @AssistedInject constructor(
    private val getAIChatResponseUseCase: GetAIChatResponseUseCase,
    private val saveNewChatUseCase: SaveNewChatUseCase,
    private val getChatHistoryUseCase: GetChatHistoryUseCase,
    private val saveMessageUseCase: SaveMessageUseCase,
    private val mainStringProvider: MainStringProvider,
    @Assisted navKey: ChatRoute
): ViewModel() {

    private val _chatSessionId = MutableStateFlow(
        if (navKey.chatId != -1L) navKey.chatId else null
    )

    private val _inputState = MutableStateFlow(TextInputUiState())

    private val _messages = MutableStateFlow<List<ChatMessageUiState>>(emptyList())

    private val _generatingMessage = MutableStateFlow<ChatMessageUiState?>(null)

    init {
        _chatSessionId.value?.let { id ->
            loadChatHistory(id)
        }
    }

    val uiState = combine(
        _inputState,
        _messages,
        _generatingMessage
    ) { input, messages, generating ->
        val allMessages = if (generating != null) messages + generating else messages
        MainUiState(
            textInputState = input,
            messages = allMessages,
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

//    fun generateAIResponse(prompt: String) {
//
//        if (prompt.isBlank()) {
//            _inputState.update { it.copy(isError = true, errorMessage = mainStringProvider.emptyPromptErrorMessage) }
//            return
//        }
//
//        val currentChatId = _chatSessionId.value
//
//        val currentTime = System.currentTimeMillis()
//
//        val userMessage = ChatMessageUiState(
//            id = 0,
//            text = prompt,
//            author = MessageAuthor.USER
//        )
//
//        val loadingAiMessage = ChatMessageUiState(
//            id = 0,
//            text = "",
//            author = MessageAuthor.AI,
//            isLoading = true
//        )
//
//        _messages.update { it + userMessage + loadingAiMessage }
//
//        _inputState.update {
//            it.copy(
//                text = "",
//                isEnabled = false,
//                isTrailingIconEnabled = false,
//                isError = false
//            )
//        }
//
//        viewModelScope.launch {
//
//            val id = if (currentChatId == null) {
//                val newId = saveNewChatUseCase(
//                    MainChatSession(
//                        id =0L,
//                        title = prompt.take(20),
//                        isFavorite = false,
//                        createdAt = LocalDate.now()
//                    )
//                )
//                _chatSessionId.value = newId
//                loadChatHistory(newId)
//                newId
//            } else currentChatId
//
//            val userChatMessage = ChatMessage(
//                id = id,
//                text = prompt,
//                author = MessageAuthor.USER
//            )
//
//            saveMessageUseCase(userChatMessage)
//
//            _inputState.update { it.copy(text = "", isEnabled = false, isTrailingIconEnabled = false) }
//
//            _generatingMessage.value = ChatMessageUiState(
//                id = -1,
//                text = "",
//                author = MessageAuthor.AI,
//                isLoading = true,
//                timestamp = System.currentTimeMillis()
//            )
//
//            try {
//
//                val historyForAi = _messages.value.map { it.toDomain() }
//
//                getAIChatResponseUseCase(historyForAi, prompt)
//                    .collect { response ->
//                        _generatingMessage.update { it?.copy(text = response, isLoading = true) }
//                    }
//
//                val finalAiText = _generatingMessage.value?.text ?: ""
//
//                val aIChatMessage = ChatMessage(
//                    id = id,
//                    text =finalAiText,
//                    author = MessageAuthor.AI
//                )
//
//                saveMessageUseCase(aIChatMessage)
//                _generatingMessage.value = null
//
//            } catch (e: ConnectionException) {
//                _generatingMessage.update { it?.copy(isLoading = false, isError = true, errorText = e.message) }
//            } finally {
//                _inputState.update {
//                    it.copy(
//                        isEnabled = true,
//                        isTrailingIconEnabled = true
//                    )
//                }
//            }
//        }
fun generateAIResponse(prompt: String) {
    if (prompt.isBlank()) {
        _inputState.update { it.copy(isError = true, errorMessage = mainStringProvider.emptyPromptErrorMessage) }
        return
    }

    val currentChatId = _chatSessionId.value
    // ФІКСУЄМО ЧАС ОДИН РАЗ ТУТ
    val currentTime = System.currentTimeMillis()

    _inputState.update {
        it.copy(text = "", isEnabled = false, isTrailingIconEnabled = false, isError = false)
    }

    viewModelScope.launch {
        try {
            val id = if (currentChatId == null) {
                val newId = saveNewChatUseCase(
                    MainChatSession(
                        id = 0L,
                        title = prompt.take(20),
                        isFavorite = false,
                        createdAt = LocalDate.now()
                    )
                )
                _chatSessionId.value = newId
                loadChatHistory(newId) // Починаємо слухати базу
                newId
            } else currentChatId

            // 1. Зберігаємо повідомлення користувача з ФІКСОВАНИМ часом
            val userChatMessage = ChatMessage(
                id = id, // Тут передаємо chatId (або id повідомлення, якщо воно генерується БД)
                text = prompt,
                author = MessageAuthor.USER,
                timestamp = currentTime // ПЕРЕДАЄМО ЧАС
            )
            saveMessageUseCase(userChatMessage)

            // 2. Стан генерації для ШІ
            val aiStartTime = System.currentTimeMillis() // Окремий час для відповіді ШІ
            _generatingMessage.value = ChatMessageUiState(
                id = -1,
                text = "",
                author = MessageAuthor.AI,
                isLoading = true,
                timestamp = aiStartTime // ПЕРЕДАЄМО ЧАС У UI STATE
            )

            val historyForAi = _messages.value.map { it.toDomain() }

            getAIChatResponseUseCase(historyForAi, prompt).collect { response ->
                _generatingMessage.update { it?.copy(text = response) }
            }

            val finalAiText = _generatingMessage.value?.text ?: ""

            // 3. Зберігаємо фінальну відповідь ШІ з тим же часом, коли вона почалася
            val aIChatMessage = ChatMessage(
                id = id,
                text = finalAiText,
                author = MessageAuthor.AI,
                timestamp = aiStartTime // ЗБЕРІГАЄМО ТОЙ ЖЕ ЧАС У БАЗУ
            )

            saveMessageUseCase(aIChatMessage)
            _generatingMessage.value = null

        } catch (e: Exception) {
            _generatingMessage.update {
                it?.copy(isLoading = false, isError = true, errorText = e.message)
            }
        } finally {
            _inputState.update { it.copy(isEnabled = true, isTrailingIconEnabled = true) }
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

