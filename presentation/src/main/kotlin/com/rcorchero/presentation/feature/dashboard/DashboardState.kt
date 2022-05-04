package com.rcorchero.presentation.feature.dashboard

import com.rcorchero.domain.model.User
import com.rcorchero.presentation.base.State

data class DashboardState(
    val status: Status,
    val model: DashboardModel
) : State {

    enum class Status {
        LOADING, ERROR, LOADED
    }

    companion object {
        fun empty() =
            DashboardState(
                status = Status.LOADING,
                model = DashboardModel.empty()
            )
    }
}

data class DashboardModel(
    val users: List<User>,
    val goToDetail: (Int) -> Unit
) {
    companion object {
        fun empty() =
            DashboardModel(
                users = emptyList(),
                goToDetail = {}
            )
    }
}