package com.nagot.sky.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nagot.sky.di.scope.ViewModelKey
import com.nagot.sky.factory.ViewModelFactory
import com.nagot.sky.ui.fragments.movies.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    internal abstract fun bindMoviesViewModels(moviesViewModel: MoviesViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}