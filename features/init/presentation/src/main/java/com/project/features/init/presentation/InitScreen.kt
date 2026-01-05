package com.project.features.init.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.MediumVerticalSpace
import com.project.core.theme.components.ContainerView
import com.project.core.theme.components.ProgressButton
import com.project.features.init.domain.entities.KeyFeature

@Composable
fun InitScreen() {

    val viewModel: InitViewModel = hiltViewModel()
    val loadResult by viewModel.stateFlow.collectAsStateWithLifecycle()

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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val keyFeature = state.keyFeature

        Text(
            text = keyFeature.title
        )
        MediumVerticalSpace()
        Text(
            text = keyFeature.description
        )
        MediumVerticalSpace()
        ProgressButton(
            onClickAction = onLetsGo,
            text = "Lets Go",
            isInProgress = state.isChekAuthInProgress
        )

    }

}

@Preview(showSystemUi = true)
@Composable
private fun InitContentPreview() {
    InitContent(
        state = InitUiState(
            keyFeature = KeyFeature(
                id = 1,
                title = "Hello",
                description = "Ahahahaha"
            ),
            isChekAuthInProgress = true
        ),
        onLetsGo = {}
    )
}