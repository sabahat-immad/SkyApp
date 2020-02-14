package com.nagot.sky.data

import com.nagot.sky.models.Resource
import com.nagot.sky.models.Resource.Error
import com.nagot.sky.models.Resource.Success
import com.nagot.sky.models.entity.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMoviesRepository @Inject constructor(
    private val mMoviesRemoteDataSource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRepository {

    override suspend fun getMovies(): Resource<List<Movie>> {

        return withContext(ioDispatcher) {

            val moviesList = fetchMoviesFromApi()

            moviesList.let {

                if (it is Success) {
                    return@withContext it
                } else {
                    return@withContext Error(Exception("List is empty"))
                }
            }
        }
    }

    private suspend fun fetchMoviesFromApi(): Resource<List<Movie>> {

        when (val remoteMovies = mMoviesRemoteDataSource.getMovies()) {

            is Error -> Timber.w("Remote data source fetch failed")

            is Success -> {
                return remoteMovies
            }
            else -> throw IllegalStateException()
        }

        return Error(Exception("Error fetching from remote and local"))
    }
}