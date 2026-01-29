# AIChat
**AIChat** is a high-performance Android application designed for seamless interaction with Google’s Gemini AI. Built with **Clean Architecture** principles, it provides a robust, reactive, and user-friendly experience for AI-driven conversations.
## Architecture
The project follows **Clean Architecture** patterns to ensure a strict separation of concerns:

- **Presentation Layer:** Built with **Jetpack Compose** and **MVVM**. It uses `StateFlow` and `combine` operators to provide a reactive UI that stays in sync with the underlying data.
- **Domain Layer:** The "brain" of the app, containing pure Kotlin business logic, Use Cases, and Domain Models. It remains independent of any frameworks.
- **Data Layer:** Responsible for data fetching and persistence. It integrates **Room Database** for local history and the **Google Generative AI SDK** for AI communication.
- **Glue Layer:** A specialized integration layer that maps technical SDK exceptions into meaningful, localized Domain exceptions, ensuring the UI remains stable even during API failures.

## Tech Stack

- **Language:** Kotlin (Coroutines & Flow for asynchronous streams).
- **UI Framework:** Jetpack Compose (Material 3).
- **Dependency Injection:** Hilt
- **Database:** Room (for persistent chat history). 
- **AI Integration:** Google Generative AI SDK (Gemini).
- **Navigation:** Compose Navigation 3 with Type-Safe Routes.

## Features

### Chats (History Management)
The Chats screen allows users to manage their conversation history. All chats are stored locally, allowing for quick access and offline browsing of previous interactions.

- **Features:** List of recent chats, favorite conversations.
- **Persistence:** Fully backed by Room DB.

### AI Conversation (Chat Screen)
The core experience where users interact with the Gemini AI. It features a reactive input field and a message list that supports real-time streaming.

- **Streaming:** AI responses appear as they are generated.

### Prompt Details
This feature details the process of creating prompts, both general and task-specific.

- **Template:** A description of what a well-formed prompt should look like 
- **Prompt Structure:** Template for writing a well-structured prompt
- **Examples:** List of ready-to-use prompts
