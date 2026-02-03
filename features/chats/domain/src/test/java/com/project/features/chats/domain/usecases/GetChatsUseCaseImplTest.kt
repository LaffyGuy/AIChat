package com.project.features.chats.domain.usecases

import com.project.essentials.LoadResult
import com.project.features.chats.domain.mocks.MockChatRepository
import com.project.features.chats.domain.stubs.createChatsList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetChatsUseCaseImplTest {

    private lateinit var mockChatRepository: MockChatRepository
    private lateinit var useCase: GetChatsUseCaseImpl

    @Before
    fun setUp() {
        mockChatRepository = MockChatRepository()
        useCase = GetChatsUseCaseImpl(mockChatRepository)
    }


    @Test
    fun `GIVEN chats list WHEN invoke() THEN return success with correct data`() = runTest {

        val expectedResult = createChatsList()
        mockChatRepository.resultToReturn = LoadResult.Success(expectedResult)


        val result = useCase().first()
        assertEquals(LoadResult.Success(expectedResult), result)


    }

    @Test
    fun `GIVEN exception WHEN invoke() THEN return error load result`() = runTest {
        val exception = Exception("Test crash")
        mockChatRepository.resultToReturn = LoadResult.Error(exception)

        val result = useCase().first()
        assertEquals(LoadResult.Error(exception), result)
    }

}