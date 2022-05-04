package com.rcorchero.data.source

import arrow.core.left
import com.rcorchero.data.source.remote.UserRemoteDataSource
import com.rcorchero.domain.responses.ServerError
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    private lateinit var sut: UserRepositoryImpl

    @MockK
    private lateinit var remoteDataSource: UserRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        sut = UserRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `getUsers should call to getUsers remoteDataSource`() = runBlocking {
        // Given
        coEvery { remoteDataSource.getUsers() } returns ServerError.left()

        // When
        sut.getUsers()

        // Then
        coVerify { remoteDataSource.getUsers() }
    }

    @Test
    fun `getUser should call to getUser remoteDataSource`() = runBlocking {
        // Given
        coEvery { remoteDataSource.getUser(any()) } returns ServerError.left()

        // When
        sut.getUser(userId = 1)

        // Then
        coVerify { remoteDataSource.getUser(any()) }
    }
}