package com.project.aichat

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.os.LocaleListCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.project.aichat.appstart.AppRoot
import com.project.core.theme.material.theme.AIChatTheme
import com.project.navigation.host.NavigationRoot
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val model = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel("gemini-2.5-flash")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val themeViewModel: ThemeViewModel = hiltViewModel()
            val isDark by themeViewModel.isDark.collectAsStateWithLifecycle()
            val language by themeViewModel.language.collectAsStateWithLifecycle()

//                LocaleHelper.updateLocale(this@MainActivity, language)
//                recreate()

                AIChatTheme(darkTheme = isDark) {
                    AppRoot()
                }
//                val scroll = rememberScrollState()
//
//                val scope = rememberCoroutineScope()
//
//                var input by remember { mutableStateOf("How are you today ?") }
//                var result by remember { mutableStateOf("Response will appear here") }
//
//                Column(
//                    modifier = Modifier.fillMaxSize().verticalScroll(scroll),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//
//                    Text(
//                        text = result
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    TextField(
//                        value = input,
//                        onValueChange = {
//                            input = it
//                        }
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(
//                        onClick = {
//                              scope.launch {
//                                  try {
//                                      val response = model.generateContent(input)
//                                      result = response.text ?: "Empty response"
//                                  } catch (e: Exception) {
//                                      result = "Error: ${e.message}"
//                                  }
//                              }
//                        }
//                    ) {
//                        Text(text = "Go")
//                    }
//                }
//                InitScreen()
        }
    }
}
