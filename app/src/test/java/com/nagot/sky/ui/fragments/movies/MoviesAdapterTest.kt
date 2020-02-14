package com.nagot.sky.ui.fragments.movies

import com.nagot.sky.base.BaseTest
import com.nagot.sky.models.entity.Movie
import com.nagot.sky.utils.DataGenerationUtil
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasProperty
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class MoviesAdapterTest: BaseTest() {

    @Test
    fun moviesAdapter_getMoviesFromListBasedOnTitle_trueReturned() {

        val filteredList = ArrayList<Movie>()

        val query = "ju"

        filteredList.addAll(

            DataGenerationUtil.getMovieList().filter { movie: Movie ->

                movie.title.contains(query.trim(), true)
            }
        )

        assertThat(
            filteredList, containsInAnyOrder(
                hasProperty("title", `is`("Jumanji: welcome to the jungle")),
                hasProperty("title", `is`("The Jungle Book"))

            )
        )
    }

    @Test
    fun moviesAdapter_getMoviesFromListBasedOnGenreAction_trueReturned() {

        val filteredList = ArrayList<Movie>()

        val query = "acti"

        filteredList.addAll(

            DataGenerationUtil.getMovieList().filter { movie: Movie ->

                movie.genre.contains(query.trim(), true)
            }
        )

        assertThat(
            filteredList, containsInAnyOrder(
                hasProperty("title", `is`("Jumanji: welcome to the jungle")),
                hasProperty("title", `is`("John Wick"))

            )
        )
    }

    @Test
    fun moviesAdapter_getMoviesFromListBasedOnGenreFantasy_trueReturned() {

        val filteredList = ArrayList<Movie>()

        val query = "fa"

        filteredList.addAll(

            DataGenerationUtil.getMovieList().filter { movie: Movie ->

                movie.genre.contains(query.trim(), true)
            }
        )

        assertThat(
            filteredList, containsInAnyOrder(
                hasProperty("title", `is`("The Jungle Book")),
                hasProperty("title", `is`("Star Wars: The Last Jedi"))

            )
        )
    }

    @Test
    fun moviesAdapter_getMoviesFromListBasedOnTitleAndGenreQueryTextA_trueReturned() {

        val filteredList = ArrayList<Movie>()

        val query = "a"

        filteredList.addAll(

            DataGenerationUtil.getMovieList().filter { movie: Movie ->

                movie.title.contains(query.trim(), true) ||
                        movie.genre.contains(query.trim(), true)
            }
        )

        assertThat(
            filteredList, containsInAnyOrder(
                hasProperty("title", `is`("Jumanji: welcome to the jungle")),
                hasProperty("title", `is`("The Jungle Book")),
                hasProperty("title", `is`("Star Wars: The Last Jedi")),
                hasProperty("title", `is`("John Wick"))

            )
        )
    }
}