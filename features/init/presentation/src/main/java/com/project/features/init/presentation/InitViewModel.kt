package com.project.features.init.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.essentials.handler.ExceptionHandler
import com.project.features.init.domain.ShowKeyFeaturesUseCase
import com.project.features.init.domain.entities.KeyFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InitViewModel @Inject constructor(
    getKeyFeatureUseCase: ShowKeyFeaturesUseCase,
    private val exceptionHandler: ExceptionHandler
) : ViewModel() {


    private val _effects = MutableStateFlow(Effects())
    val effects: StateFlow<Effects> = _effects

    private val vmStateFlow = MutableStateFlow(ViewModelState())

    val stateFlow: StateFlow<LoadResult<InitUiState>> = flow {
        emit(LoadResult.Loading)
        try {
            getKeyFeatureUseCase().collect { result ->
                when(result) {
                    is ShowKeyFeaturesUseCase.Result.Show -> {
                       emit(LoadResult.Success(InitUiState(result.data)))
                    }
                    ShowKeyFeaturesUseCase.Result.Skip -> {
                        letsGo()
                    }
                }
            }
        } catch (e: Exception) {
            emit(LoadResult.Error(e))
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = LoadResult.Loading
        )

    fun letsGo() {
            _effects.update { it.copy(launchMainScreen = Unit) }
    }

    fun onLaunchMainScreenProcessed() {
        _effects.update { it.copy(launchMainScreen = null) }
    }


}


data class InitUiState(
    val keyFeatures: List<KeyFeature>,
    val isChekAuthInProgress: Boolean = false
)

private data class ViewModelState(
    val isChekAuthInProgress: Boolean = false
)

data class Effects(
    val launchMainScreen: Unit? = null
)
