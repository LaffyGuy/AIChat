package com.project.features.init.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.essentials.LoadResult
import com.project.features.init.domain.GetKeyFeatureUseCase
import com.project.features.init.domain.entities.KeyFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class InitViewModel @Inject constructor(
    getKeyFeatureUseCase: GetKeyFeatureUseCase
): ViewModel() {

    val stateFlow: StateFlow<LoadResult<InitUiState>> =
        getKeyFeatureUseCase
            .invoke()
            .map { loadResult ->
                when(loadResult) {
                    LoadResult.Loading -> LoadResult.Loading
                    is LoadResult.Success -> {
                        LoadResult.Success(InitUiState(loadResult.data))
                    }
                    is LoadResult.Error -> {
                        LoadResult.Error(loadResult.exception)
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = LoadResult.Loading
            )


}


data class InitUiState(
    val keyFeature: KeyFeature
)
