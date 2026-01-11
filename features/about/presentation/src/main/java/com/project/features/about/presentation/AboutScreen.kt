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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.LargeVerticalSpace
import com.project.core.theme.MediumVerticalSpace
import com.project.core.theme.SmallVerticalSpace
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.features.about.presentation.components.LanguageDropdownMenu
import com.project.features.about.presentation.components.ThemeToggle

@Composable
fun AboutScreen() {

    val viewModel: AboutViewModel = hiltViewModel()
    val isDark by viewModel.isDark.collectAsStateWithLifecycle()
    val language by viewModel.language.collectAsStateWithLifecycle()
    AboutContent(
        isDarkTheme = isDark,
        onSwitchTheme = viewModel::toggleTheme,
        selectedLanguage = language,
        onSelectLanguage = viewModel::changeLanguage
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
            text = stringResource(R.string.about_welcome_ai_chat),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        MediumVerticalSpace()

        Text(
            text = stringResource(R.string.about_about_app),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 20.sp
        )

        SmallVerticalSpace()

        Text(
            text = stringResource(R.string.about_help_with_prompts),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 20.sp
        )

        LargeVerticalSpace()

        Text(
            text = stringResource(R.string.about_about_model_title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        SmallVerticalSpace()

        Text(
            text = stringResource(R.string.about_about_model_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 20.sp
        )

        LargeVerticalSpace()

        HorizontalDivider()

        MediumVerticalSpace()

        Text(
            text = stringResource(R.string.about_language_title),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
           Text(text = stringResource(R.string.about_language))
            LanguageDropdownMenu(
                selectedLanguage = selectedLanguage,
                onSelectLanguage = onSelectLanguage
            )
        }

        MediumVerticalSpace()

        HorizontalDivider()

        MediumVerticalSpace()

        Text(
            text = stringResource(R.string.about_settings_title),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(R.string.about_theme_mode))
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