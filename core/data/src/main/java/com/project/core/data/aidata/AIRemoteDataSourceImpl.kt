package com.project.core.data.aidata

import com.google.firebase.ai.FirebaseAI
import javax.inject.Inject

class AIRemoteDataSourceImpl @Inject constructor(
    firebaseAI: FirebaseAI
) {

    val model = firebaseAI.generativeModel("gemini-2.5-flash")

    suspend fun generateResponse(prompt: String): String {
        val response = model.generateContent(prompt)
        return response.text.orEmpty()
    }

}