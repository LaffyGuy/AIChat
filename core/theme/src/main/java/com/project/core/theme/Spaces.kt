package com.project.core.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SmallVerticalSpace() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun MediumVerticalSpace() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun LargeVerticalSpace() {
    Spacer(modifier = Modifier.height(24.dp))
}