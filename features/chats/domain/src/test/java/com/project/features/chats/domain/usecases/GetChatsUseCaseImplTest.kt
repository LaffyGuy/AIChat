package com.project.features.chats.domain.usecases

import com.project.essentials.LoadResult
import com.project.features.chats.domain.entities.ChatSession
import com.project.features.chats.domain.mocks.MockChatRepository
import com.project.features.chats.domain.stubs.createChatsList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class GetChatsUseCaseImplTest {

    private lateinit var mockChatRepository: MockChatRepository
    private lateinit var useCase: GetChatsUseCaseImpl

    @Before
    fun setUp() {
        mockChatRepository = MockChatRepository()
        useCase = GetChatsUseCaseImpl(mockChatRepository)
    }


    @Test
    fun `GIVEN chats list WHEN invoke() THEN return success witj correct data`() = runTest {

        val expectedResult = LoadResult.Success(createChatsList())

        val job = launch {
            mockChatRepository.chatsFlow.emit(expectedResult)
        }
        val actualResult = useCase().first()

        assert(actualResult is LoadResult.Success)
        assertEquals(createChatsList(), (actualResult as LoadResult.Success).data)
        assertEquals(1, actualResult.data.size)
        assertEquals("Hello", actualResult.data[0].title)

        job.cancel()
    }

}