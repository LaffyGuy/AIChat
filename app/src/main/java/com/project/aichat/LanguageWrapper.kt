package com.project.aichat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

//@Composable
//fun LanguageWrapper(
//    languageCode: String,
//    content: @Composable () -> Unit
//) {
//    val context = LocalContext.current
//    val localizedContext = remember(languageCode) {
//        LocaleHelper.updateContext(context, languageCode)
//    }
//
//    CompositionLocalProvider(LocalContext provides localizedContext) {
//        content()
//    }
//}