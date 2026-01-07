package com.project.core.theme.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.core.theme.material.theme.AIChatTheme

@Composable
fun PreviewScreenContent(
    content: @Composable () -> Unit
) {
    AIChatTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}