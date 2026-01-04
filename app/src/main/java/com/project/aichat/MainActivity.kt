package com.project.aichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.project.aichat.ui.theme.AIChatTheme
import com.project.essentials.logger.Logger
import com.project.essentials.resources.StringResources
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var stringResources: StringResources


    private val model = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel("gemini-2.5-flash")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        logger.d("dsd3sdsdsdsd")

        val appName = stringResources.getString(R.string.app_name)
        Logger.d(appName)

        enableEdgeToEdge()
        setContent {
            AIChatTheme {

                val scroll = rememberScrollState()

                val scope = rememberCoroutineScope()

                var input by remember { mutableStateOf("How are you today ?") }
                var result by remember { mutableStateOf("Response will appear here") }

                Column(
                    modifier = Modifier.fillMaxSize().verticalScroll(scroll),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = result
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = input,
                        onValueChange = {
                            input = it
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                              scope.launch {
                                  try {
                                      val response = model.generateContent(input)
                                      result = response.text ?: "Empty response"
                                  } catch (e: Exception) {
                                      result = "Error: ${e.message}"
                                  }
                              }
                        }
                    ) {
                        Text(text = "Go")
                    }
                }
            }
        }
    }
}
