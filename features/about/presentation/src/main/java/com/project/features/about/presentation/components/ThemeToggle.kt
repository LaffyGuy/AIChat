package com.project.features.about.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThemeToggle(
    isDarkTheme: Boolean,
    onSwitchTheme: (Boolean) -> Unit
) {
    val switchWidth = 60.dp
    val switchHeight = 36.dp
    val thumbSize = 32.dp

    val thumbOffset by animateDpAsState(
        targetValue = if (isDarkTheme) switchWidth - thumbSize - 2.dp else 2.dp
    )

    Box(
        modifier = Modifier
            .width(switchWidth)
            .height(switchHeight)
            .clip(RoundedCornerShape(18.dp))
            .background(if (isDarkTheme) Color.DarkGray else Color.LightGray)
            .clickable { onSwitchTheme(!isDarkTheme) },
        contentAlignment = Alignment.CenterStart

    ) {
        Box(
            modifier = Modifier
                .size(thumbSize)
                .offset(x = thumbOffset)
                .clip(CircleShape)
                .background(if (isDarkTheme) Color.White else Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isDarkTheme) "Dark" else "Light",
                color = if(isDarkTheme) Color.Black else Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ThemeToggleLightModePreview() {
    ThemeToggle(
        isDarkTheme = false,
        onSwitchTheme = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ThemeToggleNightModePreview() {
    ThemeToggle(
        isDarkTheme = true,
        onSwitchTheme = {}
    )
}