package com.project.navigation.common.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


interface Route : NavKey

@Serializable
data object InitRoute : Route

@Serializable
data object MainRoute : Route

@Serializable
data object AboutRoute: Route

@Serializable
data object PromptsRoute: Route
