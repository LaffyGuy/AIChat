package com.project.features.about.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.features.about.domain.ChangeThemeUseCase
import com.project.features.about.domain.GetThemeSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val changeThemeUseCase: ChangeThemeUseCase,
    getThemeSettingsUseCase: GetThemeSettingsUseCase
): ViewModel() {

    val isDark: StateFlow<Boolean> =
        getThemeSettingsUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = false
            )


    fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch {
            changeThemeUseCase(isDark)
        }
    }

}