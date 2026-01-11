package com.project.features.about.domain

import kotlinx.coroutines.flow.Flow

interface ChangeLanguageUseCase {

    suspend operator fun invoke(language: String)

}