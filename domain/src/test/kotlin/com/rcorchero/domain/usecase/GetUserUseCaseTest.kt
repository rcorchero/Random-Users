package com.rcorchero.domain.usecase

import arrow.core.right
import com.rcorchero.domain.common.FakeData
import com.rcorchero.domain.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserUseCaseTest {

    private lateinit var sut: GetUserUseCase

    @MockK
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        sut = GetUserUseCase(
            repository = repository
        )
    }

    @Test
    fun `when UseCase is called, should get users from repository`() = runBlocking {
        // Given
        coEvery { repository.getUser(any()) } returns FakeData.mockUserList().first().right()

        // When
        sut.invoke(userId = 1)

        // Then
        coVerify { repository.getUser(userId = 1) }
    }
}