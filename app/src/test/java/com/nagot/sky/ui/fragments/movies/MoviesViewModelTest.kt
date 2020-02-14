package com.nagot.sky.ui.fragments.movies

import com.nagot.sky.base.BaseTest
import com.nagot.sky.data.DefaultMoviesRepository
import com.nagot.sky.data.MoviesRepository
import com.nagot.sky.models.Resource
import com.nagot.sky.utils.DataGenerationUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MoviesViewModelTest: BaseTest() {

    @Mock
    private lateinit var mDefaultMoviesRepository: MoviesRepository

    private val mMoviesViewModel by lazy {

        MoviesViewModel(mDefaultMoviesRepository)
    }

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        mDefaultMoviesRepository = mock(DefaultMoviesRepository::class.java)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun moviesViewModel_checkMoviesLiveDataDataLoadingLiveDataAndSnackbarText_trueReturned() =
        runBlockingTest {

            `when`(mDefaultMoviesRepository.getMovies()).thenReturn(DataGenerationUtil.getResource())

            mMoviesViewModel.loadMovies()

            assertThat(
                mMoviesViewModel.movies.value, containsInAnyOrder(
                    hasProperty("title", `is`("Jumanji: welcome to the jungle")),
                    hasProperty("title", `is`("The Jungle Book")),
                    hasProperty("title", `is`("Star Wars: The Last Jedi")),
                    hasProperty("title", `is`("John Wick"))
                )
            )

            assertThat(
                mMoviesViewModel.dataLoading.value, `is`(false)
            )

            assertThat(
                mMoviesViewModel.snackbarText.value, `is`(nullValue())
            )
        }

    @ExperimentalCoroutinesApi
    @Test
    fun moviesViewModel_checkMoviesLiveDataEmptyDataLoadingLiveDataAndSnackbarText_trueReturned() =
        runBlockingTest {

            `when`(mDefaultMoviesRepository.getMovies()).thenReturn(Resource.Error(Exception()))

            mMoviesViewModel.loadMovies()

            assertThat(
                mMoviesViewModel.movies.value, `is`(emptyList())
            )

            assertThat(
                mMoviesViewModel.dataLoading.value, `is`(false)
            )

            assertThat(
                mMoviesViewModel.snackbarText.value, `is`(notNullValue())
            )
        }
}