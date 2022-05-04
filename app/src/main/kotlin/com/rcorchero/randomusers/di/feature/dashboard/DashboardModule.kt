package com.rcorchero.randomusers.di.feature.dashboard

import com.rcorchero.domain.usecase.GetUsersUseCase
import com.rcorchero.presentation.asynchrony.AsynchronyManager
import com.rcorchero.presentation.feature.dashboard.DashboardNavigator
import com.rcorchero.presentation.feature.dashboard.DashboardStore
import com.rcorchero.randomusers.di.asynchrony.AsynchronyModule
import com.rcorchero.randomusers.feature.dashboard.DashboardActivity
import com.rcorchero.randomusers.feature.dashboard.DashboardNavigatorImpl
import dagger.Module
import dagger.Provides

@Module(includes = [AsynchronyModule::class])
class DashboardModule {

    @Provides
    internal fun provideStore(
        getUsersUseCase: GetUsersUseCase,
        navigator: DashboardNavigator,
        asynchronyManager: AsynchronyManager
    ): DashboardStore =
        DashboardStore(
            getUsersUseCase = getUsersUseCase,
            navigator = navigator,
            asynchronyManager = asynchronyManager
        )

    @Provides
    internal fun provideNavigator(
        view: DashboardActivity
    ): DashboardNavigator =
        DashboardNavigatorImpl(view)
}