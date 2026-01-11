package com.project.aichat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.data.LanguageDataRepository
import com.project.data.ThemeDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    themeDataRepository: ThemeDataRepository,
    languageDataRepository: LanguageDataRepository
): ViewModel() {

    val isDark: StateFlow<Boolean> =
        themeDataRepository.getTheme()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = false
        )

    val language: StateFlow<String> =
        languageDataRepository.getLanguage()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = "en"
            )

}