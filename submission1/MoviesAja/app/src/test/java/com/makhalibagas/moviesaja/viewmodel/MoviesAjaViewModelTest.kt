package com.makhalibagas.moviesaja.viewmodel

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MoviesAjaViewModelTest {

    private lateinit var viewmodel : MoviesAjaViewModel

    @Before
    fun setup(){
        viewmodel = MoviesAjaViewModel()
    }

    @Test
    fun testListMoviesAja() {
        val moviesAja = viewmodel.listMoviesAja()
        assertNotNull(moviesAja)
        assertEquals(10, moviesAja.size)
    }

    @Test
    fun testListTvShowsAja() {
        val tvshowsAja = viewmodel.listTvShowsAja()
        assertNotNull(tvshowsAja)
        assertEquals(10, tvshowsAja.size)
    }
}