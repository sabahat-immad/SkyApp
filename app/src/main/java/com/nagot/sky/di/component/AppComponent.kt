package com.nagot.sky.di.component

import android.app.Application
import com.nagot.sky.di.module.ActivityModule
import com.nagot.sky.di.module.DataModule
import com.nagot.sky.di.module.NetworkModule
import com.nagot.sky.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    DataModule::class])

interface AppComponent: AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)
}