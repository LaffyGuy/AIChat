package com.project.features.prompts.presentation.promptsdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.features.prompts.domain.entities.PromptSample

@Composable
fun PromptsDetailsScreen() {

}

@Composable
fun PromptsDetailsContent(
    prompt: PromptSample
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(

        ) {
            Text(
                text = prompt.title
            )
        }
    }

}

@ScreenPreview
@Composable
fun PromptsDetailsContentPreview() {
    PreviewScreenContent {
        PromptsDetailsContent(
            prompt = PromptSample(
                id = 1,
                title = "Hello",
                promptSample = listOf("", ""),
                promptStructure = listOf("", ""),
                promptsExample = listOf("", "")
            )
        )
    }
}