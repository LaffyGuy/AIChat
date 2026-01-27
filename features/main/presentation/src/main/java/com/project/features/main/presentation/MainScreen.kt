package com.project.features.main.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.features.main.presentation.components.ChatMessageBubble

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    MainContent(
        uiState = state,
        onGenerateClick = viewModel::generateAIResponse,
        onTextChanged = viewModel::onTextChanged,

        )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    uiState: MainUiState,
    onGenerateClick: (String) -> Unit,
    onTextChanged: (String) -> Unit
) {

    val listState = rememberLazyListState()
    LaunchedEffect(uiState.messages.size) {
        if (uiState.messages.isNotEmpty()) {
            listState.animateScrollToItem(uiState.messages.size - 1)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            if (uiState.messages.isEmpty() && uiState.shouldShowWelcomeItem) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    WelcomeItem()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    contentPadding = PaddingValues(bottom = 8.dp),
                    state = listState
                ) {
                    items(uiState.messages) { message ->
                        ChatMessageBubble(message)
                    }
                }
            }
        }

        ChatTextField(
            value = uiState.textInputState.text,
            hint = uiState.textInputState.hint,
            onValueChange = onTextChanged,
            isTrailingIconEnabled = uiState.textInputState.isTrailingIconEnabled,
            onGenerateClick = onGenerateClick,
            isError = uiState.textInputState.isError,
            errorTextFieldMessage = uiState.textInputState.errorMessage,
            isEnabled = uiState.textInputState.isEnabled,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun WelcomeItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.size(width = 350.dp, height = 210.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Hello!",
                fontSize = 18.sp
            )
            Text(
                text = stringResource(R.string.welcome_item_text1) +
                        stringResource(R.string.welcome_item_text2) +
                        stringResource(R.string.welcome_item_text3) +
                        stringResource(R.string.welcome_item_text4),
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                color = Color.Black
            )
        }

    }
}

@Composable
fun ChatTextField(
    value: String,
    hint: String,
    isTrailingIconEnabled: Boolean,
    modifier: Modifier = Modifier,
    onGenerateClick: (String) -> Unit,
    isError: Boolean,
    errorTextFieldMessage: String?,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = value,
            placeholder = {
                Text(
                    text = hint,
                    color = colorResource(R.color.medium_gray)
                )
            },
            onValueChange = onValueChange,
            enabled = isEnabled,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                errorCursorColor = Color.Red,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,
            ),
            trailingIcon = if (isTrailingIconEnabled) {
                {
                    Icon(
                        painter = painterResource(R.drawable.ic_send),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { onGenerateClick(value) }
                    )
                }
            } else null,
            isError = isError,

            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
    if (isError) {
        Text(
            text = errorTextFieldMessage ?: "",
            color = Color.Red,
            fontSize = 12.sp
        )
    }

}

@Preview(showSystemUi = true)
@Composable
private fun WelcomeItemPreview() {
    PreviewScreenContent {
        WelcomeItem()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ChatTextFieldPreview() {
    PreviewScreenContent {
        ChatTextField(
            hint = "Hello",
            isTrailingIconEnabled = true,
            onGenerateClick = {},
            isError = false,
            errorTextFieldMessage = null,
            value = "",
            onValueChange = {},
            isEnabled = false
        )
    }
}

@ScreenPreview
@Composable
fun MainContentPreview() {
    PreviewScreenContent {
        MainContent(
            uiState = MainUiState(
                textInputState = TextInputUiState(
                    text = "",
                    hint = "Hello",
                    isEnabled = true,
                    isTrailingIconEnabled = false,
                    isError = false,
                )
            ),
            onGenerateClick = {},
            onTextChanged = {

            }
        )
    }

}