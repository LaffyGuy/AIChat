package com.project.essentials.dialogs

interface Dialogs {

    suspend fun showAlertDialog(config: DialogConfig): Boolean

}