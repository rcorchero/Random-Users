package com.rcorchero.presentation.feature.dashboard

import com.rcorchero.presentation.base.Action

sealed class DashboardAction : Action

object LoadDashboard : DashboardAction()

data class OnSuccessLoadDashboard(
    val model: DashboardModel
) : DashboardAction()
object OnErrorLoadDashboard : DashboardAction()