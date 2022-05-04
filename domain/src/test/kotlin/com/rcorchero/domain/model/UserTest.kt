package com.rcorchero.domain.model

import com.rcorchero.domain.common.FakeData.mockUserList
import org.junit.Test

class UserTest {

    private val firstUser = mockUserList().first()

    @Test
    fun `getFullAddress should full formatted address`() {
        // Given
        val addressExpected =
            "${firstUser.address.street}, ${firstUser.address.suite}. ${firstUser.address.city} (${firstUser.address.zipcode})"

        // Then
        assert(firstUser.address.getFullAddress() == addressExpected)
    }

    @Test
    fun `getTags should get tags list`() {
        // Given
        val tagListExpected = listOf("harness", "real-time", "e-markets")

        // Then
        assert(firstUser.company.getTags().first() == tagListExpected.first())
    }
}