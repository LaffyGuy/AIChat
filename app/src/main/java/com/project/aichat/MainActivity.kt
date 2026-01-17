package com.project.aichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.aichat.appstart.AppRoot
import com.project.core.theme.material.theme.AIChatTheme
import com.project.essentials.exceptions.ConnectionException
import com.project.essentials.exceptions.ExceptionToMessageMapper
import com.project.essentials.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var exceptionToMessageMapper: ExceptionToMessageMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val message = exceptionToMessageMapper.getLocalizedMessage(ConnectionException())
        Logger.d("AAAA $message")

        setContent {

            val themeViewModel: ThemeViewModel = hiltViewModel()
            val isDark by themeViewModel.isDark.collectAsStateWithLifecycle()

            AIChatTheme(darkTheme = isDark) {
                AppRoot()
            }
        }
    }
}
