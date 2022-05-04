package com.rcorchero.randomusers.di.app

import android.app.Application
import com.rcorchero.randomusers.AndroidApp
import com.rcorchero.randomusers.di.ActivityBuilder
import com.rcorchero.randomusers.di.data.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        RepositoryModule::class,
        ActivityBuilder::class,
    ]
)
interface AppComponent : AndroidInjector<AndroidApp> {

    override fun inject(app: AndroidApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}