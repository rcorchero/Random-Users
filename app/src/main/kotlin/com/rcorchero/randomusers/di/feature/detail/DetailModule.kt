package com.rcorchero.randomusers.di.feature.detail

import com.rcorchero.domain.usecase.GetUserUseCase
import com.rcorchero.presentation.asynchrony.AsynchronyManager
import com.rcorchero.presentation.feature.detail.DetailNavigator
import com.rcorchero.presentation.feature.detail.DetailStore
import com.rcorchero.randomusers.di.asynchrony.AsynchronyModule
import com.rcorchero.randomusers.feature.detail.DetailActivity
import com.rcorchero.randomusers.feature.detail.DetailNavigatorImpl
import dagger.Module
import dagger.Provides

@Module(includes = [AsynchronyModule::class])
class DetailModule {

    @Provides
    internal fun provideStore(
        view: DetailActivity,
        getUserUseCase: GetUserUseCase,
        navigator: DetailNavigator,
        asynchronyManager: AsynchronyManager
    ): DetailStore =
        DetailStore(
            getUserUseCase = getUserUseCase,
            args = view.getArgs,
            navigator = navigator,
            asynchronyManager = asynchronyManager
        )

    @Provides
    internal fun provideNavigator(
        view: DetailActivity
    ): DetailNavigator =
        DetailNavigatorImpl(view)
}