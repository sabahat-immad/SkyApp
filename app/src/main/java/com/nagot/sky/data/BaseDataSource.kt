package com.nagot.sky.data

import com.nagot.sky.models.Resource
import com.nagot.sky.models.entity.Movie

interface BaseDataSource {

    suspend fun getMovies(): Resource<List<Movie>>
}