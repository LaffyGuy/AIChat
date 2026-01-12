package com.project.features.prompts.presentation.promptssample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.Dimens
import com.project.core.theme.LargeVerticalSpace
import com.project.core.theme.MediumVerticalSpace
import com.project.core.theme.SmallVerticalSpace
import com.project.core.theme.components.ContainerView
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.presentation.promptssample.components.PromptItem

@Composable
fun PromptsSampleScreen(
    onNavigateToDetailsScreen: (Long) -> Unit
) {

    val viewModel: PromptsSampleViewModel = hiltViewModel()
    val loadResult by viewModel.stateFlow.collectAsStateWithLifecycle()
//    val effects by viewModel.effects.collectAsStateWithLifecycle()
//
//    LaunchedEffect(effects.launchDetailsScreen) {
//        if(effects.launchDetailsScreen != null) {
//            viewModel.onLaunchDetailsScreen()
//            viewModel.onLaunchDetailsScreenProcessed()
//        }
//    }

    ContainerView(
        loadResult = loadResult,
        onTryAgain = {},
        content = { promptsSampleList ->
            PromptsSampleContent(
                prompts = promptsSampleList.data,
                onClick = onNavigateToDetailsScreen
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

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Dimens.MediumPadding,
                        vertical = Dimens.LargePadding
                    )
            ) {

                // Title
                Text(
                    text = "Prompt Examples",
                    style = MaterialTheme.typography.titleLarge
                )

                MediumVerticalSpace()

                // Primary description
                Text(
                    text = "A curated collection of structured prompts designed to improve the quality and accuracy of AI responses.",
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )

                SmallVerticalSpace()

                // Secondary description (lighter)
                Text(
                    text = "Each example demonstrates role definition, context, constraints, and expected output format.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    lineHeight = 18.sp
                )

                LargeVerticalSpace()

                HorizontalDivider()
            }
        }

        items(
            items = prompts,
            key = { it.id }
        ) { prompt ->
            PromptItem(
                promptSample = prompt,
                onClick = onClick
            )
        }

        item {
            LargeVerticalSpace()
        }
    }

//    LazyColumn(
//        modifier = Modifier.fillMaxSize()
//    ) {
//
//        item {
//
//            Box(
//                modifier = Modifier.fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "Prompt Examples",
//                    style = MaterialTheme.typography.titleLarge,
//                    modifier = Modifier.padding(Dimens.MediumPadding)
//                )
//            }
//
//
//            MediumVerticalSpace()
//
//            Text(
//                text = "Explore well-structured prompt examples designed to help you communicate more effectively with AI." +
//                        " Each example shows how to clearly define your goal, context, and constraints to receive accurate and high-quality responses.",
//                style = MaterialTheme.typography.bodyMedium,
//                lineHeight = 20.sp,
//                modifier = Modifier.padding(Dimens.MediumPadding)
//            )
//
//            SmallVerticalSpace()
//
//            Text(
//                text = "Use these prompts as templates, customize them for your needs, and apply them directly in your AI conversations.",
//                style = MaterialTheme.typography.bodyMedium,
//                lineHeight = 20.sp,
//                modifier = Modifier.padding(Dimens.MediumPadding)
//            )
//
//            LargeVerticalSpace()
//
//        }
//
//        items(prompts) { prompt ->
//            PromptItem(
//                promptSample = prompt,
//                onClick = onClick
//            )
//        }
//    }

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
                ), PromptSample(
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