package com.project.features.about.domain

import kotlinx.coroutines.flow.Flow

interface GetThemeSettingsUseCase {

    operator fun invoke(): Flow<Boolean>

}