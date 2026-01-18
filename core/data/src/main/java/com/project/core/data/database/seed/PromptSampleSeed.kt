package com.project.core.data.database.seed

import com.project.core.data.database.model.PromptSampleEntity

object PromptSampleSeed {

    fun getSamples(): List<PromptSampleEntity> = listOf(
        PromptSampleEntity(
            id = 1,
            title = "Programming Prompts",
            promptSample = listOf("A good programming prompt clearly describes the problem, the programming language, the expected output, and any constraints. It should also include the developer’s skill level and context such as mobile app, backend, or algorithm development. Avoid vague requests like “fix my code”. Instead, explain what you want to achieve, where the problem occurs, and what result you expect."),
            promptStructure = listOf("You are a [role].\nI am working with [language / framework].\nMy goal is to [specific task].\nConstraints: [rules, limitations].\nPlease provide [type of solution]."),
            promptExample = listOf("You are an experienced Android developer.\nI’m building a Kotlin app using Jetpack Compose.\nExplain how to structure navigation using a single NavHost.\nPlease include clear examples.","Act as a senior backend engineer.\nWrite a clean REST API example in Kotlin using Ktor.\nInclude authentication in the solution.","Explain this Kotlin code step by step.\nFocus on readability and performance improvements.\nProvide practical suggestions.")
        ),
        PromptSampleEntity(
            id = 2,
            title = "Marketing Prompts",
            promptSample = listOf("Marketing prompts should define the target audience, goal, tone, and platform. The more specific the audience and context, the better the output. Always mention who the content is for, what action you want the user to take, and where the content will be used."),
            promptStructure = listOf("You are a marketing expert.\nTarget audience: [who].\nGoal: [what you want to achieve].\nPlatform: [where it will be used].\nTone: [style]."),
            promptExample = listOf("You are a digital marketing expert.\nCreate a short onboarding message for a mobile AI app.\nTarget audience: students.\nTone: friendly and motivating.","You are a digital marketing expert.\nWrite a Google Play Store description for an AI chat app.\nFocus on productivity and ease of use.","You are a digital marketing expert.\nCreate 5 catchy push notification ideas.\nGoal: re-engage inactive users of a learning app.")
        ),
        PromptSampleEntity(
            id = 3,
            title = "Design Prompts",
            promptSample = listOf("Design prompts work best when they describe style, purpose, platform, and visual constraints. Reference colors, mood, target users, and inspiration when possible. Even without images, clear visual language helps the AI generate better ideas."),
            promptStructure = listOf("You are a UI/UX designer.\nDesign goal: [what].\nPlatform: [Android / Web / iOS].\nStyle: [modern, minimal, colorful].\nConstraints: [colors, layout rules]."),
            promptExample = listOf("You are a UI/UX designer.\nDesign an onboarding screen for an AI chat app.\nStyle: modern, minimal, friendly.\nPlatform: Android.","You are a UI/UX designer.\nSuggest a color palette and typography.\nApp focus: productivity.\nPlatform: mobile.","You are a UI/UX designer.\nDescribe an icon set style.\nThe app is AI-based and modern.")
        ),
        PromptSampleEntity(
            id = 4,
            title = "Education & Learning Prompts",
            promptSample = listOf("Educational prompts should specify the learner’s level, topic, and learning goal. Asking for step-by-step explanations improves clarity and retention. You can also request examples, quizzes, or analogies to enhance understanding."),
            promptStructure = listOf("You are a teacher.\nStudent level: [beginner / intermediate / advanced].\nTopic: [subject].\nGoal: [what the student should learn]."),
            promptExample = listOf("You are a programming tutor.\nExplain Kotlin coroutines to a beginner.\nUse simple analogies and examples.","You are a teacher.\nExplain the basics of AI and machine learning.\nAssume the student is at high school level.","You are an Android instructor.\nCreate a short lesson plan.\nTopic: clean architecture in Android development.")
        ),
        PromptSampleEntity(
            id = 5,
            title = "Writing & Creativity Prompts",
            promptSample = listOf("Creative prompts should define genre, tone, length, and purpose. You can also add constraints to guide creativity without limiting it too much, helping the AI produce more focused and original results."),
            promptStructure = listOf("You are a creative writer.\nGenre: [type].\nTone: [mood].\nLength: [short / medium].\nPurpose: [why]."),
            promptExample = listOf("You are a creative writer.\nWrite a short motivational story.\nTheme: learning new skills in technology.","You are a creative writer.\nGenerate 10 creative app name ideas.\nThe app is an AI-powered chat application.","You are a creative writer.\nWrite a friendly welcome message.\nAudience: first-time users of an AI assistant app.")
        ),

    )


}