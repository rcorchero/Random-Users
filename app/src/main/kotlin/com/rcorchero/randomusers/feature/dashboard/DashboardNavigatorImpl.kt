package com.rcorchero.randomusers.feature.dashboard

import com.rcorchero.presentation.feature.dashboard.DashboardNavigator
import com.rcorchero.randomusers.feature.detail.DetailActivity
import com.rcorchero.randomusers.ui.utils.animateInToRight

class DashboardNavigatorImpl(
    private val activity: DashboardActivity
) : DashboardNavigator {

    override fun goToDetail(userId: Int) {
        activity.apply {
            startActivity(
                DetailActivity(
                    context = activity,
                    userId = userId
                )
            )
            animateInToRight()
        }
    }
}