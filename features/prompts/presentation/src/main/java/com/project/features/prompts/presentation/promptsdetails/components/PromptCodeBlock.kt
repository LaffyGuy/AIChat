package com.project.features.prompts.presentation.promptsdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core.theme.Dimens
import com.project.core.theme.FontSize
import com.project.core.theme.LargeVerticalSpace
import com.project.core.theme.Shapes
import com.project.core.theme.previews.PreviewScreenContent
import com.project.features.prompts.presentation.R

@Composable
fun PromptCodeBlock(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.LightGray,
                shape = Shapes.SmallRoundedCornerShape
            )
            .padding(Dimens.MediumPadding)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.prompt_template),
                color = Color.Gray,
                fontSize = FontSize.MediumFontSize,
                modifier = Modifier.weight(1f)
            )
        }

        LargeVerticalSpace()

        text
            .split("\n")
            .forEach { line ->
                Text(
                    text = line,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Monospace,
                    fontSize = FontSize.MediumFontSize,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PromptCodeBlockPreview() {
    PreviewScreenContent {
        PromptCodeBlock(
            text = "This prompt type helps you clearly define your intent, context, and constraints to get high-quality AI responses. This prompt type helps you clearly define your intent, context, and constraints to get high-quality AI responses."
        )
    }
}