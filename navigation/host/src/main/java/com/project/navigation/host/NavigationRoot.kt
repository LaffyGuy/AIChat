package com.project.navigation.host

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.project.features.about.presentation.AboutScreen
import com.project.features.main.presentation.MainScreen
import com.project.features.prompts.presentation.promptsdetails.PromptsDetailsScreen
import com.project.features.prompts.presentation.promptsdetails.PromptsDetailsViewModel
import com.project.features.prompts.presentation.promptssample.PromptsSampleScreen
import com.project.navigation.Navigator
import com.project.navigation.TOP_LEVEL_DESTINATION
import com.project.navigation.common.routes.AboutRoute
import com.project.navigation.common.routes.MainRoute
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
        startRoute = MainRoute,
        topLevelRoutes = TOP_LEVEL_DESTINATION.keys
    )

    val navigator = remember {
        Navigator(navigationState)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(
                selectedKey = navigationState.topLevelRoute,
                onSelectKey = {
                    navigator.navigate(it)
                }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            onBack = navigator::goBack,
            entries = navigationState.toEntries(
                entryProvider {
                    entry<MainRoute> { MainScreen() }
                    entry<AboutRoute> { AboutScreen() }
                    entry<PromptsRoute> {
                        PromptsSampleScreen(
                            onNavigateToDetailsScreen = { promptId ->
                                navigator.navigate(PromptDetailsRoute(promptId))
                            }
                        )
                    }
                    entry<PromptDetailsRoute> { key ->
                        val viewModel = hiltViewModel<PromptsDetailsViewModel, PromptsDetailsViewModel.Factory>(
                            creationCallback = { factory ->
                                factory.create(key)
                            }
                        )
                        PromptsDetailsScreen(
                             viewModel = viewModel
                        )
                    }
                }
            )
        )
    }
}