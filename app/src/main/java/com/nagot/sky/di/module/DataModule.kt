package com.nagot.sky.di.module

import com.nagot.sky.data.DefaultMoviesRepository
import com.nagot.sky.data.MoviesRepository
import com.nagot.sky.data.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDefaultMoviesRepository(
        moviesRemoteDataSource: RemoteDataSource
    ): MoviesRepository {

        return DefaultMoviesRepository(moviesRemoteDataSource)
    }
}