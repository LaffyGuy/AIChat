package com.project.features.prompts.presentation.promptsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.features.prompts.domain.GetPromptSampleDetailsByIdUseCase
import com.project.features.prompts.domain.entities.PromptSample
import com.project.navigation.common.routes.PromptDetailsRoute
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PromptsDetailsViewModel.Factory::class)
class PromptsDetailsViewModel @AssistedInject constructor(
  private val getPromptSampleDetailsByIdUseCase: GetPromptSampleDetailsByIdUseCase,
    @Assisted navKey: PromptDetailsRoute
): ViewModel() {

    private val _promptFlow = MutableStateFlow<LoadResult<PromptDetailsUiState>>(LoadResult.Loading)
    val promptFlow: StateFlow<LoadResult<PromptDetailsUiState>> = _promptFlow

    init {
        viewModelScope.launch {
            _promptFlow.value = LoadResult.Loading
            try {
                val promptDetails = getPromptSampleDetailsByIdUseCase(navKey.promptId)
                _promptFlow.value = LoadResult.Success(PromptDetailsUiState(promptDetails))
            } catch (e: Exception) {
                _promptFlow.value = LoadResult.Error(e)
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(navKey: PromptDetailsRoute): PromptsDetailsViewModel
    }

}

data class PromptDetailsUiState(
    val prompt: PromptSample
)