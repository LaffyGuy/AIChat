package com.project.features.prompts.presentation.promptsdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.Dimens
import com.project.core.theme.LargeVerticalSpace
import com.project.core.theme.MediumVerticalSpace
import com.project.core.theme.components.ContainerView
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.essentials.logger.Logger
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.presentation.R
import com.project.features.prompts.presentation.promptsdetails.components.BulletItem
import com.project.features.prompts.presentation.promptsdetails.components.PromptCodeBlock
import com.project.features.prompts.presentation.promptsdetails.components.SectionTitle

@Composable
fun PromptsDetailsScreen(viewModel: PromptsDetailsViewModel) {

//    val viewModel: PromptsDetailsViewModel = hiltViewModel()
    val loadResult by viewModel.promptFlow.collectAsStateWithLifecycle()

    ContainerView(
        loadResult = loadResult,
        onTryAgain = {},
        content = { promptDetails ->
            PromptsDetailsContent(
                prompt = promptDetails.prompt
            )
        }
    )
}

@Composable
fun PromptsDetailsContent(
    prompt: PromptSample
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding)
    ) {

        // ─── TITLE ──────────────────────────────────────────────
        item {
            Text(
                text = prompt.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = Dimens.LargePadding)
            )

            LargeVerticalSpace()
        }

        // ─── DESCRIPTION ────────────────────────────────────────
        item {
            Text(
                text = stringResource(R.string.hello_prompt),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 20.sp
            )

            LargeVerticalSpace()
        }

        if (prompt.promptSample.isNotEmpty()) {
            item {
                SectionTitle(text = stringResource(R.string.prompt_template))
                MediumVerticalSpace()
            }

            items(prompt.promptSample) { template ->
                Logger.d("AAAA - promptSample - $template")
                Text(
                    text = template,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            item {
                LargeVerticalSpace()
            }
        }

        // ─── STRUCTURE ──────────────────────────────────────────
        if (prompt.promptStructure.isNotEmpty()) {
            item {
                SectionTitle(text = stringResource(R.string.prompt_structure))
                MediumVerticalSpace()
            }

            items(prompt.promptStructure) { structureItem ->
                Logger.d("AAAA - promptStructure - $structureItem")
                PromptCodeBlock(
                    text = structureItem,
                    modifier = Modifier.padding(bottom = Dimens.MediumPadding)
                )
            }

            item {
                LargeVerticalSpace()
            }
        }

        // ─── TEMPLATE ───────────────────────────────────────────

        // ─── EXAMPLES ─────────────────────────────────────────
        if (prompt.promptsExample.isNotEmpty()) {
            item {
                SectionTitle(text = stringResource(R.string.examples))
                MediumVerticalSpace()
            }

            items(prompt.promptsExample) { example ->
                Logger.d("AAAA - promptExample - $example")
                 BulletItem(
                     text = example
                 )
            }
        }

        item {
            LargeVerticalSpace()
        }
    }

//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//
//        ) {
//            Text(
//                text = prompt.title
//            )
//        }
//    }

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