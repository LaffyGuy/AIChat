package com.project.features.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.essentials.exceptions.ConnectionException
import com.project.features.main.domain.GetAIChatResponseUseCase
import com.project.features.main.domain.GetRecipeAIResponseUseCase
import com.project.features.main.domain.entities.ChatMessage
import com.project.features.main.domain.entities.MessageAuthor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAIChatResponseUseCase: GetAIChatResponseUseCase,
    private val getRecipeAIResponseUseCase: GetRecipeAIResponseUseCase
): ViewModel() {

    private val _inputState = MutableStateFlow(TextInputUiState())

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())

    val _aiResponse = MutableStateFlow<LoadResult<String>>(LoadResult.Loading)

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

        val userMessage = ChatMessage(
            text = prompt,
            author = MessageAuthor.USER
        )

        val loadingAiMessage = ChatMessage(
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
            try {
                val response = getAIChatResponseUseCase(prompt)

                _messages.update { list ->
                    list.map {
                        if (it.isLoading && it.author == MessageAuthor.AI) {
                            it.copy(
                                text = response ?: "No response",
                                isLoading = false
                            )
                        } else it
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

//    fun generateRecipeResponse(prompt: String, promptType: Prompts) {
//
//        if (prompt.isBlank()) {
//            _inputState.update { it.copy(isError = true, errorMessage = "Prompt cannot be empty") }
//            return
//        }
//
//        val userMessage = ChatMessage(
//            text = prompt,
//            author = MessageAuthor.USER
//        )
//
//        _messages.update { it + userMessage }
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
//        _aiResponse.value = LoadResult.Loading
//
//        viewModelScope.launch {
//            try {
//                val response = when(promptType) {
//                    Prompts.ONE -> {
//                        _inputState.update { it.copy(hint = "Write ingredients") }
//                        getAIChatResponseUseCase(prompt)
//                    }
//                    Prompts.TWO -> {
//                        _inputState.update { it.copy(hint = "Key words") }
//                        getRecipeAIResponseUseCase(prompt)
//
//                    }
////                    Prompts.THREE -> {}
////                    Prompts.FOUR -> {}
//                }
////                val response = getRecipeAIResponseUseCase(prompt)
//
//                val aiMessage = ChatMessage(
//                    text = response,
//                    author = MessageAuthor.AI
//                )
//
//                _messages.update { it + aiMessage }
//                _aiResponse.value = LoadResult.Success(response)
//            } catch (e: Exception) {
//                _aiResponse.value = LoadResult.Error(e)
//            } finally {
//                _inputState.update {
//                    it.copy(
//                        isEnabled = true,
//                        isTrailingIconEnabled = true
//                    )
//                }
//            }
//        }
//    }
//
}

data class MainUiState(
    val textInputState: TextInputUiState = TextInputUiState(),
    val messages: List<ChatMessage> = emptyList(),
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

