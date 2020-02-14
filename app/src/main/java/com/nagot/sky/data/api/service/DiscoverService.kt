package com.nagot.sky.data.api.service

import com.nagot.sky.models.network.MoviesApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface DiscoverService {

    @GET("/api/movies/")
    suspend fun fetchMovies(): Response<MoviesApiResponse>
}