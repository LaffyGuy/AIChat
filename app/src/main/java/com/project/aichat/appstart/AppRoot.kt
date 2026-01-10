package com.project.aichat.appstart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.features.init.presentation.InitScreen
import com.project.navigation.host.NavigationRoot

@Composable
fun AppRoot() {

    val viewModel: AppStartViewModel = hiltViewModel()
    val start by viewModel.start.collectAsStateWithLifecycle()


    when(start) {
        StartDestination.Init -> {
            InitScreen(
                onNavigateToMainScreen = {
                       viewModel.onInitCompleted()
                }
            )
        }
        StartDestination.Main -> {
            NavigationRoot()
        }

        else -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }


}