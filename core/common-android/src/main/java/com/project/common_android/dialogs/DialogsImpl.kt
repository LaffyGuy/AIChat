package com.project.common_android.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.project.essentials.dialogs.DialogConfig
import com.project.essentials.dialogs.Dialogs
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


@ActivityRetainedScoped
class DialogsImpl: Dialogs {

    private val dialogsState = mutableStateOf<DialogRecord?>(null)

    override suspend fun showAlertDialog(config: DialogConfig): Boolean {
        return suspendCancellableCoroutine { continuation ->
             val record = DialogRecord(
                 config = config,
                 onConfirm = {
                     continuation.resume(true)
                     dialogsState.value = null
                 },
                 onDismiss = {
                     continuation.resume(false)
                     dialogsState.value = null
                 }
             )
            dialogsState.value = record

            continuation.invokeOnCancellation {
                dialogsState.value = null
            }
        }
    }

    @Composable
    fun DialogHost() {
        dialogsState.value?.Renderer()
    }

    @Composable
    private fun DialogRecord.Renderer() {

        val negative = config.negativeButton

        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = onConfirm
                ) {
                    Text(text = config.positiveButton)
                }
            },
            dismissButton = if (!negative.isNullOrBlank()) {
                {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(text = negative)
                    }
                }
            } else {
                null
            },
            title = {
                Text(text = config.title)
            },
            text = {
                Text(text = config.message)
            }
        )
    }

    private data class DialogRecord(
        val config: DialogConfig,
        val onConfirm: () -> Unit,
        val onDismiss: () -> Unit
    )

}