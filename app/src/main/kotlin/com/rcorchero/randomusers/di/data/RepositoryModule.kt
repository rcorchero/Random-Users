package com.rcorchero.randomusers.di.data

import com.rcorchero.data.source.UserRepositoryImpl
import com.rcorchero.data.source.remote.UserRemoteDataSource
import com.rcorchero.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module(includes = [RemoteDataModule::class])
class RepositoryModule {

    @Provides
    internal fun provideRepository(
        remoteDataSource: UserRemoteDataSource
    ): UserRepository =
        UserRepositoryImpl(remoteDataSource)
}