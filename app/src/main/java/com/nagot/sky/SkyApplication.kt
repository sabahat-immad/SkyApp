package com.nagot.sky

import com.nagot.sky.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class SkyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val component = DaggerAppComponent.builder().application(this).build()

        component.inject(this)

        return component
    }
}