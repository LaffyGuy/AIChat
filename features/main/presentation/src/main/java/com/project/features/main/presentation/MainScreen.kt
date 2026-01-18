package com.project.features.main.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.core.theme.SmallVerticalSpace
import com.project.core.theme.components.LoadResultView
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.essentials.entities.MessageAuthor
import com.project.features.main.presentation.components.ChatMessageBubble
import com.project.features.main.presentation.components.ReadyPromptList
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = hiltViewModel()

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    MainContent(
        uiState = state,
        prompts = state.readyPromptsList,
        onGenerateClick = viewModel::generateAIResponse,
        onTextChanged = viewModel::onTextChanged,

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    uiState: MainUiState,
    prompts: List<ReadyPrompt>,
    onGenerateClick: (String) -> Unit,
    onTextChanged: (String) -> Unit
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    val scope = rememberCoroutineScope()
    var isSheetVisible by remember { mutableStateOf(false) }

    if(isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isSheetVisible = false },
            sheetState = sheetState,
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            // Твоє меню ReadyPromptList
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = (LocalConfiguration.current.screenHeightDp.dp / 3)) // 1/3 екрана
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Select a ready prompt", fontWeight = FontWeight.Bold)
                SmallVerticalSpace()

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(prompts) { prompt ->
                        Text(
                            text = prompt.text,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
//                                    onPromptClick(prompt)
                                    isSheetVisible = false
                                }
                                .padding(vertical = 8.dp)
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // ── Основний контент зверху (чат або WelcomeItem) ──
        Box(
            modifier = Modifier
                .weight(1f) // займає весь доступний простір
                .fillMaxWidth()
        ) {
            if (uiState.messages.isEmpty() && uiState.shouldShowWelcomeItem) {
                // WelcomeItem по центру
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    WelcomeItem()
                }
            } else {
                // Чат
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(uiState.messages) { message ->
                        ChatMessageBubble(message)
                    }
                }
            }
        }

        // ── ReadyPromptList ──
        ReadyPromptList(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        sheetState.show() // тут ми використовуємо coroutineScope
                        isSheetVisible = true // за бажанням для логіки UI
                    }
                }
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )

        // ── Поле вводу ──
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
        modifier = modifier.size(width = 300.dp, height = 200.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
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
                    text = hint
                )
            },
            onValueChange = onValueChange,
            enabled = isEnabled,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.medium_gray),
                focusedContainerColor = Color.White,
                errorCursorColor = Color.Red,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent
            ),
            trailingIcon = if (isTrailingIconEnabled) {
                {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null,
                        modifier = Modifier.clickable { onGenerateClick(value) }
                    )
                }
            } else null,
            isError = isError,

            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        if (isError) {
            Text(
                text = errorTextFieldMessage ?: "",
                color = Color.Red,
                fontSize = 12.sp
            )
        }
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

//@ScreenPreview
//@Composable
//fun MainContentPreview() {
//    PreviewScreenContent {
//        MainContent(
//            uiState = MainUiState(
////                textInputState = TextInputUiState(
////                    "",
////                    true,
////                    true,
////                    false,
////                    null,
////                )
//            ),
//            prompts = emptyList(),
//            onGenerateClick = {},
//            onTextChanged = {
//
//            }
//        )
//    }
//
//}