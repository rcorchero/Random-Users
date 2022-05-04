package com.rcorchero.presentation.feature.dashboard

import arrow.core.left
import arrow.core.right
import com.rcorchero.domain.responses.RepositoryError
import com.rcorchero.domain.usecase.GetUsersUseCase
import com.rcorchero.presentation.common.FakeData.mockAsynchronyManager
import com.rcorchero.presentation.common.FakeData.mockUserList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class DashboardStoreTest {

    private lateinit var sut: DashboardStore

    @MockK
    private lateinit var getUsersUseCase: GetUsersUseCase

    @MockK
    private lateinit var navigator: DashboardNavigator

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        sut = DashboardStore(
            getUsersUseCase = getUsersUseCase,
            navigator = navigator,
            asynchronyManager = mockAsynchronyManager()
        )
    }

    @Test
    fun `onInit should get users`() {
        // When
        sut.onInit()

        // Then
        coVerify { getUsersUseCase() }
    }

    @Test
    fun `onInit should handle load dashboard action`() {
        // When
        sut.onInit()

        // Then
        assert(sut.getActions().last() is LoadDashboard)
    }

    @Test
    fun `onInit should trigger LOADING status`() {
        // When
        sut.onInit()

        // Then
        assert(sut.getState().value.status == DashboardState.Status.LOADING)
    }

    @Test
    fun `when dashboard is loaded successfully should handle OnSuccessLoadDashboard action`() {
        // Given
        coEvery { getUsersUseCase() } returns mockUserList().right()

        // When
        sut.onInit()

        // Then
        assert(sut.getActions().last() is OnSuccessLoadDashboard)
    }

    @Test
    fun `when dashboard is loaded successfully OnSuccessLoadDashboard should be properly formed`() {
        // Given
        val users = mockUserList()
        coEvery { getUsersUseCase() } returns users.right()

        // When
        sut.onInit()

        // Then
        assert(sut.getState().value.model.users == users)
        assert(sut.getState().value.model.goToDetail(1) == navigator.goToDetail(1))
    }

    @Test
    fun `when dashboard is loaded successfully should trigger LOADED status`() {
        // Given
        coEvery { getUsersUseCase() } returns mockUserList().right()

        // When
        sut.onInit()

        // Then
        assert(sut.getState().value.status == DashboardState.Status.LOADED)
    }

    @Test
    fun `when dashboard loading gives an error should handle OnErrorLoadDashboard action`() {
        // Given
        coEvery { getUsersUseCase() } returns RepositoryError.left()

        // When
        sut.onInit()

        // Then
        assert(sut.getActions().last() is OnErrorLoadDashboard)
    }

    @Test
    fun `when dashboard loading gives an error should trigger ERROR status`() {
        // Given
        coEvery { getUsersUseCase() } returns RepositoryError.left()

        // When
        sut.onInit()

        // Then
        assert(sut.getState().value.status == DashboardState.Status.ERROR)
    }
}