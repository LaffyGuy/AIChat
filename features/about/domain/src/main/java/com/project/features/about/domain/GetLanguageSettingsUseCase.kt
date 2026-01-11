package com.project.features.about.domain

import kotlinx.coroutines.flow.Flow

interface GetLanguageSettingsUseCase {

    operator fun invoke(): Flow<String>

}