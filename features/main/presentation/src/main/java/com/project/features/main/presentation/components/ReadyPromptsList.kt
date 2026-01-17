package com.project.features.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.core.theme.Dimens
import com.project.core.theme.previews.PreviewScreenContent
import com.project.features.main.presentation.R

@Composable
fun ReadyPromptList(
    modifier: Modifier = Modifier
) {

    Card(
       modifier = modifier
           .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.medium_gray)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.SmallPadding),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Ready for use prompts"
            )
        }
    }


}

@Preview(showSystemUi = true)
@Composable
private fun ReadyPromptListPreview() {
    PreviewScreenContent {
        ReadyPromptList()
    }
}