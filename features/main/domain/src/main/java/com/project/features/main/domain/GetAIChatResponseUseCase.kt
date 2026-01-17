package com.project.features.main.domain

interface GetAIChatResponseUseCase {

    suspend operator fun invoke(prompt: String): String?

}