package com.project.features.prompts.presentation.promptssample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.features.prompts.domain.GetPromptsSampleListUseCase
import com.project.features.prompts.domain.entities.PromptSample
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PromptsSampleViewModel @Inject constructor(private val getPromptsSampleListUseCase: GetPromptsSampleListUseCase): ViewModel() {

    val stateFlow: StateFlow<LoadResult<PromptsSampleUiState>> =
        getPromptsSampleListUseCase().map { loadResult ->
            when(loadResult) {
                LoadResult.Loading -> LoadResult.Loading
                is LoadResult.Success -> LoadResult.Success(PromptsSampleUiState(loadResult.data))
                is LoadResult.Error -> LoadResult.Error(loadResult.exception)
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = LoadResult.Loading
            )

}

data class PromptsSampleUiState(
    val data: List<PromptSample>
)

