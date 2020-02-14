package com.nagot.sky.data.api

import com.nagot.sky.data.RemoteDataSource
import com.nagot.sky.data.api.service.DiscoverService
import com.nagot.sky.models.Resource
import com.nagot.sky.models.Resource.Error
import com.nagot.sky.models.Resource.Success
import com.nagot.sky.models.entity.Movie
import com.nagot.sky.models.network.MoviesApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteMoviesDataSource @Inject constructor(
    private val mDiscoverService: DiscoverService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteDataSource {

    override suspend fun getMovies(): Resource<List<Movie>> = withContext(ioDispatcher){

        try {
            val response = mDiscoverService.fetchMovies()

            if (response.isSuccessful) {

                val moviesList: List<Movie>? = movieMapper(response.body())

                if (moviesList != null) {

                    return@withContext Success(moviesList)
                } else {

                    return@withContext Error(Exception("Movies list not found"))
                }

            } else {
                return@withContext Error(Exception("Response not found"))
            }

        } catch (e: Exception) {
            return@withContext Error(e)
        }

    }

    private fun movieMapper(response: MoviesApiResponse?): List<Movie>? {

        val movieList: MutableList<Movie> = mutableListOf()

        return response?.data?.mapTo(movieList, {

            val movie: Movie

            it.apply {
                movie = Movie(
                    id = id,
                    title = title,
                    year = year,
                    genre = genre,
                    poster = poster
                )
            }

            movie
        })
    }
}