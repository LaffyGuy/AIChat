package com.project.navigation.common.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import java.util.UUID


interface Route : NavKey

@Serializable
data object InitRoute : Route

@Serializable
data class ChatRoute(
    val chatId: Long? = null,
    val uuid: String = UUID.randomUUID().toString()
) : Route

@Serializable
data object AboutRoute: Route

@Serializable
data object PromptsRoute: Route

@Serializable
data class PromptDetailsRoute(
    val promptId: Long
): Route

@Serializable
data object ChatsRoute: Route

@Serializable
data object FavoritesRoute: Route
