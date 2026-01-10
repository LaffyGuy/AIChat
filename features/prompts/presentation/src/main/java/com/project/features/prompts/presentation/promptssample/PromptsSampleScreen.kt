package com.project.features.prompts.presentation.promptssample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview

@Composable
fun PromptsSampleScreen() {
    PromptsSampleContent(
        data = listOf()
    )
}

@Composable
fun PromptsSampleContent(
    data: List<String>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(data) {

        }
    }

}

@ScreenPreview
@Composable
private fun PromptsSampleContentPreview() {
    PreviewScreenContent {
        PromptsSampleContent(
            data = listOf()
        )
    }
}