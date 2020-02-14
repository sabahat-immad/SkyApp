package com.nagot.sky.models.network

data class MoviesApiResponse(
    val data: List<MovieResponse>
) {

    data class MovieResponse(
        val id: Int,
        val title: String,
        val year: Int,
        val genre: String,
        val poster: String
    )
}