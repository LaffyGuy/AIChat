package com.project.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.project.navigation.common.routes.AboutRoute
import com.project.navigation.common.routes.MainRoute
import com.project.navigation.common.routes.PromptsRoute
import com.project.navigation.host.R

data class BottomNavItem(
    @StringRes val title: Int,
    val icon: ImageVector
)

val TOP_LEVEL_DESTINATION = mapOf(
    MainRoute to BottomNavItem(R.string.main_screen, icon = Icons.Default.Menu),
    AboutRoute to BottomNavItem(R.string.about_screen, icon = Icons.Default.Info),
    PromptsRoute to BottomNavItem(R.string.prompts_screen, icon = Icons.Default.Edit)
)
