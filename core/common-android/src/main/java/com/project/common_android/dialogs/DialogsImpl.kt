package com.project.common_android.dialogs

import com.project.essentials.dialogs.DialogConfig
import com.project.essentials.dialogs.Dialogs
import kotlinx.coroutines.suspendCancellableCoroutine

class DialogsImpl: Dialogs {

    private val dialogsState = mutableSt

    override suspend fun showAlertDialog(config: DialogConfig): Boolean {
        return suspendCancellableCoroutine {

        }
    }
}