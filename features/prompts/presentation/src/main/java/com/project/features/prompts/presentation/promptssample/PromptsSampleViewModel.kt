package com.project.features.prompts.presentation.promptssample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.features.prompts.domain.GetPromptsSampleListUseCase
import com.project.features.prompts.domain.entities.PromptSample
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PromptsSampleViewModel @Inject constructor(private val getPromptsSampleListUseCase: GetPromptsSampleListUseCase): ViewModel() {


//    private val _effects = MutableStateFlow(Effects(null))
//    val effects: StateFlow<Effects> = _effects

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


//    fun onLaunchDetailsScreen() {
//        _effects.update { it.copy(launchDetailsScreen = Unit) }
//    }
//
//    fun onLaunchDetailsScreenProcessed() {
//        _effects.update { it.copy(launchDetailsScreen = null) }
//    }

}

data class PromptsSampleUiState(
    val data: List<PromptSample>
)

//data class Effects(
//    val launchDetailsScreen: Unit? = null
//)