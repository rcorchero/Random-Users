package com.rcorchero.presentation.feature.detail

interface DetailNavigator {

    fun navigateToDialer(phone: String)

    fun navigateToEmail(email: String)

    fun navigateToLocation(latitude: Double, longitude: Double)

    fun goBack()
}