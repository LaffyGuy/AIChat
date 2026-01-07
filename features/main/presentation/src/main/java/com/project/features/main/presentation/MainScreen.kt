package com.project.features.main.presentation

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
fun MainScreen() {
    MainContent()
}

@Composable
fun MainContent(
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Main Screen",
            fontSize = 24.sp
        )
    }
}

@ScreenPreview
@Composable
fun MainContentPreview() {
    PreviewScreenContent {
        MainContent()
    }

}