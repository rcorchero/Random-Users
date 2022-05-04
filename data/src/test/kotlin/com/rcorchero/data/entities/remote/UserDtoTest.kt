package com.rcorchero.data.entities.remote

import com.rcorchero.data.entities.common.FakeData.mockUserList
import org.junit.Test

class UserDtoTest {

    private val data = mockUserList()

    @Test
    fun `UserDto toDomain should return User well formed`() {
        // Given
        val result = data.first().toDomain()

        // Then
        assert(data.first().username == result.username)
        assert(data.first().address.city == result.address.city)
        assert(data.first().email == result.email)
        assert(data.first().id == result.id)
    }

    @Test
    fun `UserDto list toDomain should return User well formed`() {
        // Given
        val result = data.toDomain()

        // Then
        assert(data.first().username == result.first().username)
        assert(data.first().address.city == result.first().address.city)
        assert(data.first().email == result.first().email)
        assert(data.first().id == result.first().id)
    }
}