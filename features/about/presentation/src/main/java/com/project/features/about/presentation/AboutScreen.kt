package com.project.features.about.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview

@Composable
fun AboutScreen() {
    AboutContent()
}

@Composable
fun AboutContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "About Screen",
            fontSize = 24.sp
        )
    }

}

@ScreenPreview
@Composable
fun AboutContentPreview() {
    PreviewScreenContent {
        AboutContent()
    }
}