package com.project.features.prompts.presentation.promptssample.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.core.theme.R
import com.project.core.theme.SmallVerticalSpace
import com.project.core.theme.previews.PreviewScreenContent
import com.project.features.prompts.domain.entities.PromptSample
import com.project.features.prompts.presentation.R.*

@Composable
fun PromptItem(
    promptSample: PromptSample,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {

//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable {
//                onClick(promptSample.id)
//            },
//        colors = CardDefaults.cardColors(
//            Color.White
//        ),
////        elevation = CardDefaults.cardElevation(
////            defaultElevation = 8.dp
////        ),
//        shape = RoundedCornerShape(8.dp)
//    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(promptSample.id)
            }
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = promptSample.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = promptSample.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            SmallVerticalSpace()
            promptSample.promptSample.forEach { template ->
                Text(
                    text = template,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }


//    }

}

@Preview(showSystemUi = true)
@Composable
private fun PromptItemPreview() {
    PreviewScreenContent {
        PromptItem(
            promptSample = PromptSample(
                id = 1,
                title = "Hello",
                imageRes = drawable.design_image,
                promptSample = listOf(),
                promptStructure = listOf(),
                promptsExample = listOf()
            ),
            onClick = {}
        )
    }
}