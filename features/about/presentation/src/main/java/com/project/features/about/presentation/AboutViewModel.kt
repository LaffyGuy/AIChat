package com.project.features.about.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.features.about.domain.ChangeLanguageUseCase
import com.project.features.about.domain.ChangeThemeUseCase
import com.project.features.about.domain.GetLanguageSettingsUseCase
import com.project.features.about.domain.GetThemeSettingsUseCase
import com.project.features.about.domain.repositories.LanguageRepository
import com.project.features.about.domain.repositories.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val changeLanguageUseCase: ChangeLanguageUseCase,
    private val getLanguageSettingsUseCase: GetLanguageSettingsUseCase,
    private val changeThemeUseCase: ChangeThemeUseCase,
    private val getThemeSettingsUseCase: GetThemeSettingsUseCase,
//    private val themeRepository: ThemeRepository,
//    private val languageRepository: LanguageRepository
): ViewModel() {


    val isDark: StateFlow<Boolean> =
        getThemeSettingsUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = false
            )

    val language: StateFlow<String> =
        getLanguageSettingsUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = "en"
            )


    fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch {
            changeThemeUseCase(isDark)
        }
    }

    fun changeLanguage(language: String) {
        viewModelScope.launch {
            changeLanguageUseCase(language)
        }
    }
}