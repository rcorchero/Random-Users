package com.rcorchero.randomusers.di.data

import com.rcorchero.randomusers.BuildConfig
import com.rcorchero.data.source.remote.UserRemoteDataSource
import com.rcorchero.data.source.remote.UserRemoteDataSourceImpl
import com.rcorchero.data.source.remote.UserService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class RemoteDataModule {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @Provides
    @Singleton
    internal fun provideUserService(): UserService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(createClient())
            .build()
            .create(UserService::class.java)

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideRemoteDataSource(
        service: UserService
    ): UserRemoteDataSource =
        UserRemoteDataSourceImpl(service)
}