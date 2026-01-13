package com.project.features.prompts.presentation.promptsdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.Dimens
import com.project.core.theme.LargeVerticalSpace
import com.project.core.theme.MediumVerticalSpace
import com.project.core.theme.components.ContainerView
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.presentation.R
import com.project.features.prompts.presentation.promptsdetails.components.BulletItem
import com.project.features.prompts.presentation.promptsdetails.components.PromptCodeBlock
import com.project.features.prompts.presentation.promptsdetails.components.SectionTitle

@Composable
fun PromptsDetailsScreen(viewModel: PromptsDetailsViewModel) {

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = prompt.title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = Dimens.LargePadding)
        )

        LargeVerticalSpace()

        Text(
            text = stringResource(R.string.hello_prompt),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 20.sp
        )

        LargeVerticalSpace()

        // ─── PROMPT TEMPLATE ────────────────────────────────────
        if (prompt.promptSample.isNotEmpty()) {
            SectionTitle(text = stringResource(R.string.prompt_template))
            MediumVerticalSpace()

            prompt.promptSample.forEach { template ->
                Text(
                    text = template,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            LargeVerticalSpace()
        }

        // ─── STRUCTURE ──────────────────────────────────────────
        if (prompt.promptStructure.isNotEmpty()) {
            SectionTitle(text = stringResource(R.string.prompt_structure))
            MediumVerticalSpace()

            prompt.promptStructure.forEach { structureItem ->
                PromptCodeBlock(
                    text = structureItem,
                    modifier = Modifier.padding(bottom = Dimens.MediumPadding)
                )
            }

            LargeVerticalSpace()
        }

        if (prompt.promptsExample.isNotEmpty()) {
            SectionTitle(text = stringResource(R.string.examples))
            MediumVerticalSpace()

            prompt.promptsExample.forEach { example ->
                BulletItem(text = example)
            }
        }

        LargeVerticalSpace()
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