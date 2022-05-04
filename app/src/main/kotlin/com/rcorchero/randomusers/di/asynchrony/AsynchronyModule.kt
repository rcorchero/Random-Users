package com.rcorchero.randomusers.di.asynchrony

import com.rcorchero.presentation.asynchrony.AsynchronyManager
import com.rcorchero.presentation.asynchrony.AsynchronyManagerImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

@Module
class AsynchronyModule(private val job: Job = SupervisorJob()) {

    @Provides
    fun provideAsynchronyManager(): AsynchronyManager =
        AsynchronyManagerImpl(
            coroutineContext = Dispatchers.Main + job,
            io = Dispatchers.IO
        )
}