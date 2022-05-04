package com.rcorchero.data.source.remote

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UserRemoteDataSourceImplTest {

    private lateinit var sut: UserRemoteDataSourceImpl

    @MockK
    private lateinit var service: UserService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        sut = UserRemoteDataSourceImpl(
            service = service
        )
    }

    @Test
    fun `getUsers should call to getUsers service`() = runBlocking {
        // Given
        every { service.getUsers() } returns mockk()

        // When
        sut.getUsers()

        // Then
        verify { service.getUsers() }
    }

    @Test
    fun `getUser should call to getUser service`() = runBlocking {
        // Given
        every { service.getUser(any()) } returns mockk()

        // When
        sut.getUser(userId = 1)

        // Then
        verify { service.getUser(any()) }
    }
}