package com.project.navigation.host

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.project.features.about.presentation.AboutScreen
import com.project.features.chats.presentation.ChatsScreen
import com.project.features.favorites.presentation.FavoritesScreen
import com.project.features.main.presentation.MainScreen
import com.project.features.main.presentation.MainViewModel
import com.project.features.prompts.presentation.promptsdetails.PromptsDetailsScreen
import com.project.features.prompts.presentation.promptsdetails.PromptsDetailsViewModel
import com.project.features.prompts.presentation.promptssample.PromptsSampleScreen
import com.project.navigation.Navigator
import com.project.navigation.TOP_LEVEL_DESTINATION
import com.project.navigation.common.routes.AboutRoute
import com.project.navigation.common.routes.ChatRoute
import com.project.navigation.common.routes.ChatsRoute
import com.project.navigation.common.routes.FavoritesRoute
import com.project.navigation.common.routes.PromptDetailsRoute
import com.project.navigation.common.routes.PromptsRoute
import com.project.navigation.components.BottomNavigationBar
import com.project.navigation.rememberNavigationState
import com.project.navigation.toEntries

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val navigationState = rememberNavigationState(
        startRoute = ChatsRoute,
        topLevelRoutes = TOP_LEVEL_DESTINATION.keys
    )

    val navigator = remember {
        Navigator(navigationState)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            Column {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray
                )
                BottomNavigationBar(
                    selectedKey = navigationState.topLevelRoute,
                    onSelectKey = {
                        navigator.navigate(it)
                    },
                )
            }
        },
        floatingActionButton = {
            when (navigationState.currentRoute) {
                is ChatsRoute -> {
                    FloatingActionButton(
                        onClick = {
                            navigator.navigate(ChatRoute(chatId = null))
                        },
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.background
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }
                else -> Unit
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            onBack = navigator::goBack,
            entries = navigationState.toEntries(
                entryProvider {
                    entry<ChatRoute> { key ->
                        val viewModel = hiltViewModel<MainViewModel, MainViewModel.Factory>(
                            creationCallback = { factory ->
                                factory.create(key)
                            }
                        )
                        MainScreen(
                            viewModel = viewModel
                        )
                    }
                    entry<AboutRoute> { AboutScreen() }
                    entry<PromptsRoute> {
                        PromptsSampleScreen(
                            onNavigateToDetailsScreen = { promptId ->
                                navigator.navigate(PromptDetailsRoute(promptId))
                            }
                        )
                    }
                    entry<PromptDetailsRoute> { key ->
                        val viewModel =
                            hiltViewModel<PromptsDetailsViewModel, PromptsDetailsViewModel.Factory>(
                                creationCallback = { factory ->
                                    factory.create(key)
                                }
                            )
                        PromptsDetailsScreen(
                            viewModel = viewModel
                        )
                    }
                    entry<ChatsRoute> {
                        ChatsScreen(
                            onClickToChatSession = { chatId ->
                                navigator.navigate(ChatRoute(chatId = chatId))
                            }
                        )
                    }
                    entry<FavoritesRoute> {
                        FavoritesScreen(
                            onClickToChatSession = { chatId ->
                                navigator.navigate(ChatRoute(chatId = chatId))
                            }
                        )
                    }
                }
            )
        )
    }
}