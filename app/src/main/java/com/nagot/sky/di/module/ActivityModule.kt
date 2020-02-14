package com.nagot.sky.di.module

import com.nagot.sky.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityFragmentsModule::class])
    internal abstract fun contributeMainActivity(): MainActivity


}