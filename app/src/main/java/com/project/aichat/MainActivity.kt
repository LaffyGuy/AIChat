package com.project.aichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.project.aichat.ui.theme.AIChatTheme
import com.project.navigation.host.NavigationRoot
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var logger: Logger
//
//    @Inject
//    lateinit var stringResources: StringResources


    private val model = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel("gemini-2.5-flash")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



//        logger.d("dsd3sdsdsdsd")
//
//        val appName = stringResources.getString(R.string.app_name)
//        Logger.d(appName)

        enableEdgeToEdge()
        setContent {
            AIChatTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    NavigationRoot(
                        modifier = Modifier.padding(paddingValues)
                    )
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
}
