package com.nagot.sky.ui.fragments.movies

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.nagot.sky.R
import com.nagot.sky.ui.activities.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    @get:Rule
    val mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun init() {

        mActivityRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun moviesFragment_typeOnSearchViewAndCloseAfterSearch() {

        onView(withId(R.id.search_view))
            .perform(click())

        onView(withId(R.id.search_view)).perform(typeSearchViewText("star wars"))
    }

    private fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return text
            }

            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }
}