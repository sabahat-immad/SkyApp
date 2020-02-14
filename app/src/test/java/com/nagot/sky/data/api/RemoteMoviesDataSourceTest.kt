package com.nagot.sky.data.api

import com.nagot.sky.base.BaseTest
import com.nagot.sky.data.api.service.DiscoverService
import com.nagot.sky.utils.DataGenerationUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.*
import org.hamcrest.collection.IsIterableContainingInAnyOrder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RemoteMoviesDataSourceTest : BaseTest() {

    @Mock
    private lateinit var mDiscoveryService: DiscoverService

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        mDiscoveryService = mock(DiscoverService::class.java)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun remoteMovies_getApiResponseIsSuccessfulNotNullAndContainsData_trueReturned() =

        runBlockingTest {

            `when`(mDiscoveryService.fetchMovies())
                .thenReturn(DataGenerationUtil.getMoviesApiResponse())

            val response = mDiscoveryService.fetchMovies()

            MatcherAssert.assertThat(
                response.isSuccessful, `is`(true)
            )

            MatcherAssert.assertThat(
                response.body(), `is`(notNullValue())
            )

            MatcherAssert.assertThat(
                response.body()?.data, IsIterableContainingInAnyOrder.containsInAnyOrder(
                    hasProperty("title", `is`("Jumanji: welcome to the jungle")),
                    hasProperty("title", `is`("The Jungle Book")),
                    hasProperty("title", `is`("Star Wars: The Last Jedi")),
                    hasProperty("title", `is`("John Wick"))
                )
            )
        }
}