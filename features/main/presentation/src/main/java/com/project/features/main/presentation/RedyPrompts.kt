package com.project.features.main.presentation


enum class Prompts {
    ONE,
    TWO,
//    THREE,
//    FOUR
}

data class ReadyPrompt(
    val text: String,
    val type: Prompts
)

val readyPrompts: List<ReadyPrompt> = listOf(
    ReadyPrompt(
        "What to cook for dinner?", Prompts.ONE
    ),
    ReadyPrompt(
        "Write a short story about a cat", Prompts.TWO
    )
//    ,
//    ReadyPrompt(
//        "Explain StateFlow in Kotlin", Prompts.THREE
//    ),
//    ReadyPrompt(
//        "How to make pizza at home?" ,Prompts.FOUR
//    )
)