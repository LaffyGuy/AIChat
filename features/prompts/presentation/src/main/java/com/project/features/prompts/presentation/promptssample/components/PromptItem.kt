package com.project.features.prompts.presentation.promptssample.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.core.theme.FontSize
import com.project.core.theme.previews.PreviewScreenContent
import com.project.features.prompts.domain.entities.PromptSample

@Composable
fun PromptItem(
    promptSample: PromptSample,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(promptSample.id)
                }
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = promptSample.title,
                fontSize = FontSize.LargeFontSize,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(16.dp)
            )
            HorizontalDivider()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PromptItemPreview() {
    PreviewScreenContent {
        PromptItem(
            promptSample = PromptSample(
                id = 1,
                title = "Hello",
                promptSample = listOf(),
                promptStructure = listOf(),
                promptsExample = listOf()
            ),
            onClick = {}
        )
    }
}