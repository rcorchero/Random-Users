package com.rcorchero.randomusers.feature.detail

import com.rcorchero.presentation.feature.detail.DetailNavigator
import com.rcorchero.randomusers.ui.utils.animateOutToLeft
import com.rcorchero.randomusers.ui.utils.makePhoneCall
import com.rcorchero.randomusers.ui.utils.openLocation
import com.rcorchero.randomusers.ui.utils.sendEmail

class DetailNavigatorImpl(
    private val activity: DetailActivity
) : DetailNavigator {

    override fun navigateToDialer(phone: String) =
        activity.makePhoneCall(phone = phone)

    override fun navigateToEmail(email: String) =
        activity.sendEmail(email = email)

    override fun navigateToLocation(latitude: Double, longitude: Double) =
        activity.openLocation(latitude = latitude, longitude = longitude)

    override fun goBack() {
        activity.apply {
            finish()
            animateOutToLeft()
        }
    }
}