package com.nagot.sky.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nagot.sky.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class BaseTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()
}