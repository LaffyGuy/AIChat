package com.project.core.data.aidata

import com.google.firebase.ai.FirebaseAI
import javax.inject.Inject

class AIRemoteDataSourceImpl @Inject constructor(
    firebaseAI: FirebaseAI
) {

    private val model = firebaseAI.generativeModel("gemini-2.5-flash")

    private suspend fun response(prompt: String): String {
        return model.generateContent(prompt).text.orEmpty()
    }

    suspend fun generateResponse(prompt: String): String {
        val response = model.generateContent(prompt)
        return response.text.orEmpty()
    }

    suspend fun generateRecipe(ingredients: String): String {
        val prompt = """
            Create a detailed recipe based on these ingredients: $ingredients.
            
            Format requirements:
            - 'instructions': Provide the cooking steps as a clear list of instructions separated by newlines. Use bold formatting on the step numbers. Use Markdown.
            - 'ingredients': List all necessary items, including quantities.
            - 'prepTime', 'cookTime', 'servings': Short strings (e.g., "15 mins").
            - 'tags': Generate a list of 3-5 relevant category tags (e.g., "Healthy", "Vegan", "Gluten-Free", "Dessert", "Quick").
        """.trimIndent()

        return response(prompt)
    }

}