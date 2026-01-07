package com.project.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.project.features.init.presentation.InitScreen
import com.project.features.main.presentation.MainScreen
import com.project.navigation.common.routes.InitRoute
import com.project.navigation.common.routes.MainRoute

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {

    val backSTack = rememberNavBackStack(InitRoute)
    NavDisplay(
        backStack = backSTack,
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
              entry<InitRoute> {
                  InitScreen(
                      onNavigateToMainScreen = {
                          backSTack.add(MainRoute)
                      }
                  )
              }
            entry<MainRoute> { MainScreen() }
        }
    )

}