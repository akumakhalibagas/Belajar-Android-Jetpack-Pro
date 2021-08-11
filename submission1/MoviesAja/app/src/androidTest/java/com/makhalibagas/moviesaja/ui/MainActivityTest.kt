package com.makhalibagas.moviesaja.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.makhalibagas.moviesaja.R
import com.makhalibagas.moviesaja.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    private val dummyMovies = DataDummy.moviesAjaList()
    private val dummyTv = DataDummy.tvShowsAjaList()


    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMoviesTv(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))

        onView(withId(R.id.nav_tv)).perform(click())
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))


        onView(withId(R.id.nav_movies)).perform(click())
    }

    @Test
    fun detailMovies(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_name)).check(matches(withText(DataDummy.moviesAjaList()[3].name)))
        onView(withId(R.id.tv_overview)).check(matches(withText(DataDummy.moviesAjaList()[3].desc)))


        Espresso.pressBack()
    }

    @Test
    fun detailTv(){
        onView(withId(R.id.nav_tv)).perform(click())

        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_name)).check(matches(withText(DataDummy.tvShowsAjaList()[3].name)))
        onView(withId(R.id.tv_overview)).check(matches(withText(DataDummy.tvShowsAjaList()[3].desc)))


        Espresso.pressBack()
    }

}