package com.rcorchero.presentation.feature.detail

import com.rcorchero.domain.usecase.GetUserUseCase
import com.rcorchero.presentation.asynchrony.AsynchronyManager
import com.rcorchero.presentation.base.Store

class DetailStore(
    private val getUserUseCase: GetUserUseCase,
    private val args: DetailArgs,
    navigator: DetailNavigator,
    asynchronyManager: AsynchronyManager
) :
    AsynchronyManager by asynchronyManager,
    DetailNavigator by navigator,
    Store<DetailsAction, DetailState>(initialState = DetailState.empty()) {

    override fun onInit() {
        LoadDetails.handle()
    }

    override fun DetailsAction.reduce(currentState: DetailState): DetailState =
        when (this) {
            is LoadDetails ->
                currentState.copy(
                    status = DetailState.Status.LOADING
                )
            is OnSuccessLoadDetails ->
                currentState.copy(
                    status = DetailState.Status.LOADED,
                    model = model,
                )
            is OnErrorLoadDetails ->
                currentState.copy(
                    status = DetailState.Status.ERROR
                )
        }

    override fun DetailsAction.sideEffects(currentState: DetailState) {
        when (this) {
            is LoadDetails -> {
                if (args.isValidUserId()) {
                    launch(
                        function = { getUserUseCase(userId = args.userId) },
                        success = { user ->
                            OnSuccessLoadDetails(
                                model = DetailsModel(
                                    user = user,
                                    goBack = { goBack() },
                                    makePhoneCall = { navigateToDialer(phone = user.phone) },
                                    sendEmail = { navigateToEmail(email = user.email) },
                                    showLocation = {
                                        navigateToLocation(
                                            latitude = user.address.geo.lat,
                                            longitude = user.address.geo.lng
                                        )
                                    }
                                )
                            ).handle()
                        },
                        error = {
                            OnErrorLoadDetails.handle()
                        }
                    )
                } else {
                    OnErrorLoadDetails.handle()
                }
            }
            else -> {
                // Nothing to do
            }
        }
    }
}