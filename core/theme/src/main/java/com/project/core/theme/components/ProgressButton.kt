package com.project.core.theme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressButton(
    onClickAction: () -> Unit,
    text: String,
    isInProgress: Boolean,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Button(
            modifier = if (isInProgress) Modifier.alpha(0f) else Modifier,
            onClick = onClickAction
        ) {
            Text(text = text)
        }
        if (isInProgress) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Center)
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun ProgressButtonPreviewWithoutProgress() {
    ProgressButton(
        onClickAction = {},
        text = "Lets Go",
        isInProgress = false
    )
}

@Preview(showBackground = true)
@Composable
private fun ProgressButtonPreviewWithProgress() {
    ProgressButton(
        onClickAction = {},
        text = "Lets Go",
        isInProgress = true
    )
}