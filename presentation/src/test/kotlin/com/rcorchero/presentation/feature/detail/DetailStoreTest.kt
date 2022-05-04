package com.rcorchero.presentation.feature.detail

import arrow.core.left
import arrow.core.right
import com.rcorchero.domain.responses.RepositoryError
import com.rcorchero.domain.usecase.GetUserUseCase
import com.rcorchero.presentation.common.FakeData.mockAsynchronyManager
import com.rcorchero.presentation.common.FakeData.mockUserList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class DetailStoreTest {

    private lateinit var sut: DetailStore

    @MockK
    private lateinit var getUserUseCase: GetUserUseCase

    @MockK
    private lateinit var navigator: DetailNavigator

    @MockK
    private lateinit var args: DetailArgs

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        sut = DetailStore(
            getUserUseCase = getUserUseCase,
            navigator = navigator,
            asynchronyManager = mockAsynchronyManager(),
            args = args
        )

        every { args.userId } returns 1234
        every { args.isValidUserId() } returns true
    }

    @Test
    fun `onInit should get user`() {
        // When
        sut.onInit()

        // Then
        coVerify { getUserUseCase(any()) }
    }

    @Test
    fun `onInit should handle load details action`() {
        // When
        sut.onInit()

        // Then
        assert(sut.getActions().last() is LoadDetails)
    }

    @Test
    fun `onInit should trigger LOADING status`() {
        // When
        sut.onInit()

        // Then
        assert(sut.getState().value.status == DetailState.Status.LOADING)
    }

    @Test
    fun `when detail is loaded successfully should handle OnSuccessLoadDetails action`() {
        // Given
        coEvery { getUserUseCase(any()) } returns mockUserList().first().right()

        // When
        sut.onInit()

        // Then
        assert(sut.getActions().last() is OnSuccessLoadDetails)
    }

    @Test
    fun `when detail is loaded successfully OnSuccessLoadDetails should be properly formed`() {
        // Given
        val user = mockUserList().first()
        coEvery { getUserUseCase(any()) } returns user.right()

        // When
        sut.onInit()

        // Then
        assert(sut.getState().value.model.user == user)
    }

    @Test
    fun `when detail is loaded successfully should trigger LOADED status`() {
        // Given
        coEvery { getUserUseCase(any()) } returns mockUserList().first().right()

        // When
        sut.onInit()

        // Then
        assert(sut.getState().value.status == DetailState.Status.LOADED)
    }

    @Test
    fun `when detail gives an error should handle OnErrorLoadDetails action`() {
        // Given
        coEvery { getUserUseCase(any()) } returns RepositoryError.left()

        // When
        sut.onInit()

        // Then
        assert(sut.getActions().last() is OnErrorLoadDetails)
    }

    @Test
    fun `when detail gives an error should trigger ERROR status`() {
        // Given
        coEvery { getUserUseCase(any()) } returns RepositoryError.left()

        // When
        sut.onInit()

        // Then
        assert(sut.getState().value.status == DetailState.Status.ERROR)
    }
}