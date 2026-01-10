package com.project.features.about.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core.theme.LargeVerticalSpace
import com.project.core.theme.MediumVerticalSpace
import com.project.core.theme.SmallVerticalSpace
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.features.about.presentation.components.LanguageDropdownMenu
import com.project.features.about.presentation.components.ThemeToggle

@Composable
fun AboutScreen() {
    AboutContent(
        isDarkTheme = false,
        onSwitchTheme = {},
        selectedLanguage = "UA",
        onSelectLanguage = {}
    )
}


@Composable
fun AboutContent(
    isDarkTheme: Boolean,
    selectedLanguage: String,
    onSelectLanguage: (String) -> Unit,
    onSwitchTheme: (Boolean) -> Unit
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Welcome to Your AI Chat App!",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        MediumVerticalSpace()

        Text(
            text = "In this app, you can send your prompts directly to Gemini 2.5-Flash, a cutting-edge AI language model, and receive fast, accurate, and insightful answers to a wide variety of questions.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 20.sp
        )

        SmallVerticalSpace()

        Text(
            text = "The app also helps you learn how to craft effective prompts, so you can get the best responses from the AI for coding, marketing, design, learning, and creative tasks.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 20.sp
        )

        LargeVerticalSpace()

        Text(
            text = "About Gemini 2.5-Flash",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        SmallVerticalSpace()

        Text(
            text = "Gemini 2.5-Flash is a highly advanced AI model capable of understanding context, following complex instructions, and providing detailed, reliable responses. It combines speed and accuracy, making it ideal for both practical solutions and creative brainstorming.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 20.sp
        )

        LargeVerticalSpace()

        Text(
            text = "We designed this app to make your AI experience simple, productive, and enjoyable!",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 20.sp
        )

        LargeVerticalSpace()

        HorizontalDivider()

        MediumVerticalSpace()

        Text(
            text = "Language",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
           Text(text = "Language :")
            LanguageDropdownMenu(
                selectedLanguage = selectedLanguage,
                onSelectLanguage = {}
            )
        }

        MediumVerticalSpace()

        HorizontalDivider()

        MediumVerticalSpace()

        Text(
            text = "Setting",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Theme mode :")
            ThemeToggle(
                isDarkTheme = isDarkTheme,
                onSwitchTheme = onSwitchTheme
            )
        }

        MediumVerticalSpace()

        HorizontalDivider()
    }
}




@ScreenPreview
@Composable
fun AboutContentPreview() {
    PreviewScreenContent {
        AboutContent(
            isDarkTheme = false,
            onSwitchTheme = {},
            selectedLanguage = "UA",
            onSelectLanguage = {}
        )
    }
}