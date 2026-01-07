package com.project.features.init.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.essentials.handler.ExceptionHandler
import com.project.features.init.domain.GetKeyFeatureUseCase
import com.project.features.init.domain.IsAuthorizedUseCase
import com.project.features.init.domain.entities.KeyFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitViewModel @Inject constructor(
    getKeyFeatureUseCase: GetKeyFeatureUseCase,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val exceptionHandler: ExceptionHandler
) : ViewModel() {


    private val _effects = MutableStateFlow(Effects())
    val effects: StateFlow<Effects> = _effects

    private val vmStateFlow = MutableStateFlow(ViewModelState())

    val stateFlow: StateFlow<LoadResult<InitUiState>> = combine(
        getKeyFeatureUseCase.invoke(),
        vmStateFlow
    ) { loadResult, vmState ->
        when (loadResult) {
            LoadResult.Loading -> LoadResult.Loading
            is LoadResult.Success -> {
                LoadResult.Success(
                    InitUiState(
                        keyFeature = loadResult.data,
                        isChekAuthInProgress = vmState.isChekAuthInProgress
                    )
                )
            }

            is LoadResult.Error -> {
                LoadResult.Error(loadResult.exception)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = LoadResult.Loading
    )



//    val stateFlow: StateFlow<LoadResult<InitUiState>> =
//        getKeyFeatureUseCase
//            .invoke()
//            .map { loadResult ->
//                when(loadResult) {
//                    LoadResult.Loading -> LoadResult.Loading
//                    is LoadResult.Success -> {
//                        LoadResult.Success(InitUiState(loadResult.data))
//                    }
//                    is LoadResult.Error -> {
//                        LoadResult.Error(loadResult.exception)
//                    }
//                }
//            }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(1000),
//                initialValue = LoadResult.Loading
//            )


    fun letsGo() {
//        viewModelScope.launch {
//            vmStateFlow.update { it.copy(isChekAuthInProgress = true) }
//            try {
//                val isAuthorized = isAuthorizedUseCase()
//
//            } catch (e: Exception) {
//                ensureActive()
//                vmStateFlow.update { it.copy(isChekAuthInProgress = false) }
//                exceptionHandler.handleException(e)
//            }

            _effects.update { it.copy(launchMainScreen = Unit) }
//        }
    }

    fun onLaunchMainScreenProcessed() {
        _effects.update { it.copy(launchMainScreen = null) }
    }


}


data class InitUiState(
    val keyFeature: KeyFeature,
    val isChekAuthInProgress: Boolean = false
)

private data class ViewModelState(
    val isChekAuthInProgress: Boolean = false
)

data class Effects(
    val launchMainScreen: Unit? = null
)
