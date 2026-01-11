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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.project.core.theme.Dimens
import com.project.core.theme.FontSize
import com.project.core.theme.LargeVerticalSpace
import com.project.core.theme.Shapes
import com.project.core.theme.previews.PreviewScreenContent

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
                text = "Prompt template",
                color = Color.Gray,
                fontSize = FontSize.MediumFontSize,
                modifier = Modifier.weight(1f)
            )
        }

        LargeVerticalSpace()

        Text(
            text = text,
            color = Color.DarkGray,
            fontFamily = FontFamily.Monospace,
            fontSize = FontSize.MediumFontSize,
            lineHeight = 20.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PromptCodeBlockPreview() {
    PreviewScreenContent {
        PromptCodeBlock(
            text = "sdsdsdsd"
        )
    }
}