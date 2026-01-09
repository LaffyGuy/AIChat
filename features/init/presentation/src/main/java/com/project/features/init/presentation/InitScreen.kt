package com.project.features.init.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.MediumVerticalSpace
import com.project.core.theme.components.ContainerView
import com.project.core.theme.components.ProgressButton
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.essentials.entities.ImageSource
import com.project.features.init.domain.entities.KeyFeature
import com.project.features.init.presentation.components.KeyFeaturePager
import java.time.ZonedDateTime

@Composable
fun InitScreen(
    onNavigateToMainScreen: () -> Unit
) {

    val viewModel: InitViewModel = hiltViewModel()
    val loadResult by viewModel.stateFlow.collectAsStateWithLifecycle()
    val effects by viewModel.effects.collectAsStateWithLifecycle()

    LaunchedEffect(effects.launchMainScreen) {
        if (effects.launchMainScreen != null) {
            onNavigateToMainScreen()
            viewModel.onLaunchMainScreenProcessed()
        }
    }

    ContainerView(
        loadResult = loadResult,
        onTryAgain = {},
        content = {
            InitContent(
                state = it,
                onLetsGo = viewModel::letsGo
            )
        }
    )


}

@Composable
fun InitContent(
    state: InitUiState,
    onLetsGo: () -> Unit
) {

    KeyFeaturePager(
        keyFeatures = state.keyFeatures,
        onLetsGoAction = onLetsGo,
        modifier = Modifier.fillMaxSize()
    )
}

@ScreenPreview
@Composable
private fun InitContentPreview() {
    PreviewScreenContent {
        InitContent(
            state = InitUiState(
                keyFeatures = listOf(
                    KeyFeature(
                        id = 1,
                        title = "Hello",
                        description = "Ahahahaha",
                        image = ImageSource.Empty,
                        lastDisplayTime = ZonedDateTime.now()
                    ),
                    KeyFeature(
                        id = 1,
                        title = "Hello",
                        description = "Ahahahaha",
                        image = ImageSource.Empty,
                        lastDisplayTime = ZonedDateTime.now()
                    ),
                    KeyFeature(
                        id = 1,
                        title = "Hello",
                        description = "Ahahahaha",
                        image = ImageSource.Empty,
                        lastDisplayTime = ZonedDateTime.now()
                    ),
                )
            ),
            onLetsGo = {}
        )
    }
}