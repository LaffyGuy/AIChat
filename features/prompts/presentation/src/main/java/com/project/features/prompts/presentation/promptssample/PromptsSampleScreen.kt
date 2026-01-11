package com.project.features.prompts.presentation.promptssample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.components.ContainerView
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.presentation.promptssample.components.PromptItem

@Composable
fun PromptsSampleScreen() {

    val viewModel: PromptsSampleViewModel = hiltViewModel()
    val loadResult by viewModel.stateFlow.collectAsStateWithLifecycle()

    ContainerView(
        loadResult = loadResult,
        onTryAgain = {},
        content = { promptsSampleList ->
            PromptsSampleContent(
                prompts = promptsSampleList.data,
                onClick = {}
            )
        }
    )


}

@Composable
fun PromptsSampleContent(
    prompts: List<PromptSample>,
    onClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(prompts) { prompt ->
            PromptItem(
                promptSample = prompt,
                onClick = onClick
            )
        }
    }
}

@ScreenPreview
@Composable
private fun PromptsSampleContentPreview() {
    PreviewScreenContent {
        PromptsSampleContent(
            prompts = listOf(
                PromptSample(
                    id = 1,
                    title = "Hello",
                    promptSample = listOf("", ""),
                    promptStructure = listOf("", ""),
                    promptsExample = listOf("", "")
                ),
                PromptSample(
                    id = 1,
                    title = "Hello",
                    promptSample = listOf("", ""),
                    promptStructure = listOf("", ""),
                    promptsExample = listOf("", "")
                ),
                PromptSample(
                    id = 1,
                    title = "Hello",
                    promptSample = listOf("", ""),
                    promptStructure = listOf("", ""),
                    promptsExample = listOf("", "")
                ),
                PromptSample(
                    id = 1,
                    title = "Hello",
                    promptSample = listOf("", ""),
                    promptStructure = listOf("", ""),
                    promptsExample = listOf("", "")
                ),
                PromptSample(
                    id = 1,
                    title = "Hello",
                    promptSample = listOf("", ""),
                    promptStructure = listOf("", ""),
                    promptsExample = listOf("", "")
                ),
                PromptSample(
                    id = 1,
                    title = "Hello",
                    promptSample = listOf("", ""),
                    promptStructure = listOf("", ""),
                    promptsExample = listOf("", "")
                ),
                PromptSample(
                    id = 1,
                    title = "Hello",
                    promptSample = listOf("", ""),
                    promptStructure = listOf("", ""),
                    promptsExample = listOf("", "")
                ),PromptSample(
                    id = 1,
                    title = "Hello",
                    promptSample = listOf("", ""),
                    promptStructure = listOf("", ""),
                    promptsExample = listOf("", "")
                )
            ),
            onClick = {}
        )
    }
}