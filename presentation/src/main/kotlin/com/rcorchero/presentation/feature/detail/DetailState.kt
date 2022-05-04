package com.rcorchero.presentation.feature.detail

import com.rcorchero.domain.model.User
import com.rcorchero.presentation.base.State

data class DetailState(
    val status: Status,
    val model: DetailsModel
) : State {

    enum class Status {
        LOADING, ERROR, LOADED
    }

    companion object {
        fun empty() =
            DetailState(
                status = Status.LOADING,
                model = DetailsModel.empty()
            )
    }
}

data class DetailsModel(
    val user: User,
    val goBack: () -> Unit,
    val makePhoneCall: () -> Unit,
    val sendEmail: () -> Unit,
    val showLocation: () -> Unit
) {
    companion object {
        fun empty() =
            DetailsModel(
                user = User.empty(),
                goBack = {},
                makePhoneCall = {},
                sendEmail = {},
                showLocation = {}
            )
    }
}