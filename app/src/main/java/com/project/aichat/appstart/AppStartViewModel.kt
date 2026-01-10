package com.project.aichat.appstart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.features.init.domain.ShowKeyFeaturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppStartViewModel @Inject constructor(
    private val showKeyFeaturesUseCase: ShowKeyFeaturesUseCase
): ViewModel() {

    private val _start = MutableStateFlow<StartDestination?>(null)
    val start: StateFlow<StartDestination?> = _start

    init {
        viewModelScope.launch {
            showKeyFeaturesUseCase().collect { result ->
                _start.value = when(result) {
                    is ShowKeyFeaturesUseCase.Result.Show -> StartDestination.Init
                    ShowKeyFeaturesUseCase.Result.Skip -> StartDestination.Main
                }
            }
        }
    }

    fun onInitCompleted() {
        _start.value = StartDestination.Main
    }

}