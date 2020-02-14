package com.nagot.sky.di.module

import com.nagot.sky.ui.fragments.movies.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMoviesFragment(): MoviesFragment
}