package com.rcorchero.domain.usecase

import arrow.core.right
import com.rcorchero.domain.common.FakeData.mockUserList
import com.rcorchero.domain.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {

    private lateinit var sut: GetUsersUseCase

    @MockK
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        sut = GetUsersUseCase(
            repository = repository
        )
    }

    @Test
    fun `when UseCase is called, should get users from repository`() = runBlocking {
        // Given
        coEvery { repository.getUsers() } returns mockUserList().right()

        // When
        sut.invoke()

        // Then
        coVerify { repository.getUsers() }
    }
}