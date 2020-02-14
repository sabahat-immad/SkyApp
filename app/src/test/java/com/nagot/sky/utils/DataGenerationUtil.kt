package com.nagot.sky.utils

import com.nagot.sky.models.Resource
import com.nagot.sky.models.Resource.*
import com.nagot.sky.models.entity.Movie
import com.nagot.sky.models.network.MoviesApiResponse
import retrofit2.Response

object DataGenerationUtil {

    fun getResource(): Resource<List<Movie>> {

        return Success(getMovieList())
    }

    fun getMovieList(): List<Movie> {

        return arrayListOf(
            Movie(
                id = 1,
                title = "Jumanji: welcome to the jungle",
                year = 2017,
                genre = "Action",
                poster = "https://image.tmdb.org/t/p/w370_and_h556_bestv2/bXrZ5iHBEjH7WMidbUDQ0U2xbmr.jpg"
            ),
            Movie(
                id = 2,
                title = "The Jungle Book",
                year = 2016,
                genre = "Fantasy",
                poster = "https://raw.githubusercontent.com/cesarferreira/sample-data/master/public/posters/038027.jpg"
            ),
            Movie(
                id = 3,
                title = "Star Wars: The Last Jedi",
                year = 2017,
                genre = "Fantasy",
                poster = "https://image.tmdb.org/t/p/w370_and_h556_bestv2/kOVEVeg59E0wsnXmF9nrh6OmWII.jpg"
            ),
            Movie(
                id = 4,
                title = "John Wick",
                year = 2014,
                genre = "Action",
                poster = "https://image.tmdb.org/t/p/w370_and_h556_bestv2/5vHssUeVe25bMrof1HyaPyWgaP.jpg"
            )
        )
    }

    fun getMoviesApiResponse(): Response<MoviesApiResponse> {

        return Response.success(getMovieResponse())
    }

    private fun getMovieResponse(): MoviesApiResponse {

        return MoviesApiResponse(
            arrayListOf(
                MoviesApiResponse.MovieResponse(
                    id = 1,
                    title = "Jumanji: welcome to the jungle",
                    year = 2017,
                    genre = "Action",
                    poster = "https://image.tmdb.org/t/p/w370_and_h556_bestv2/bXrZ5iHBEjH7WMidbUDQ0U2xbmr.jpg"
                ),
                MoviesApiResponse.MovieResponse(
                    id = 2,
                    title = "The Jungle Book",
                    year = 2016,
                    genre = "Fantasy",
                    poster = "https://raw.githubusercontent.com/cesarferreira/sample-data/master/public/posters/038027.jpg"
                ),
                MoviesApiResponse.MovieResponse(
                    id = 3,
                    title = "Star Wars: The Last Jedi",
                    year = 2017,
                    genre = "Fantasy",
                    poster = "https://image.tmdb.org/t/p/w370_and_h556_bestv2/kOVEVeg59E0wsnXmF9nrh6OmWII.jpg"
                ),
                MoviesApiResponse.MovieResponse(
                    id = 4,
                    title = "John Wick",
                    year = 2014,
                    genre = "Action",
                    poster = "https://image.tmdb.org/t/p/w370_and_h556_bestv2/5vHssUeVe25bMrof1HyaPyWgaP.jpg"
                )
            )
        )
    }
}