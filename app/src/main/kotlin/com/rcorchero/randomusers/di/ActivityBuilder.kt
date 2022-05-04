package com.rcorchero.randomusers.di

import com.rcorchero.randomusers.di.feature.dashboard.DashboardModule
import com.rcorchero.randomusers.di.feature.detail.DetailModule
import com.rcorchero.randomusers.feature.dashboard.DashboardActivity
import com.rcorchero.randomusers.feature.detail.DetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(DashboardModule::class)])
    @PerActivity
    abstract fun bindDashboardActivity(): DashboardActivity

    @ContributesAndroidInjector(modules = [(DetailModule::class)])
    @PerActivity
    abstract fun bindDetailActivity(): DetailActivity
}