package com.rcorchero.presentation.feature.dashboard

import com.rcorchero.domain.usecase.GetUsersUseCase
import com.rcorchero.presentation.asynchrony.AsynchronyManager
import com.rcorchero.presentation.base.Store

class DashboardStore(
    private val getUsersUseCase: GetUsersUseCase,
    navigator: DashboardNavigator,
    asynchronyManager: AsynchronyManager
) :
    AsynchronyManager by asynchronyManager,
    DashboardNavigator by navigator,
    Store<DashboardAction, DashboardState>(initialState = DashboardState.empty()) {

    override fun onInit() {
        LoadDashboard.handle()
    }

    override fun DashboardAction.reduce(currentState: DashboardState): DashboardState =
        when (this) {
            is LoadDashboard ->
                currentState.copy(
                    status = DashboardState.Status.LOADING
                )
            is OnSuccessLoadDashboard ->
                currentState.copy(
                    status = DashboardState.Status.LOADED,
                    model = model,
                )
            is OnErrorLoadDashboard ->
                currentState.copy(
                    status = DashboardState.Status.ERROR
                )
        }

    override fun DashboardAction.sideEffects(currentState: DashboardState) {
        when (this) {
            is LoadDashboard -> {
                launch(
                    function = { getUsersUseCase() },
                    success = { users ->
                        OnSuccessLoadDashboard(
                            model = DashboardModel(
                                users = users,
                                goToDetail = { goToDetail(userId = it) },
                            )
                        ).handle()
                    },
                    error = {
                        OnErrorLoadDashboard.handle()
                    }
                )
            }
            else -> {
                // Nothing to do
            }
        }
    }
}