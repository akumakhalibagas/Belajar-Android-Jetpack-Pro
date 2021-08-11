package com.makhalibagas.moviesaja.viewmodel

import com.makhalibagas.moviesaja.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


class DetailViewModelTest {

    private lateinit var viewModel : DetailViewModel
    private val dummyMovies = DataDummy.moviesAjaList()[0]
    private val dummyTv = DataDummy.tvShowsAjaList()[0]
    private val moviesId = dummyMovies.id
    private val tvId = dummyTv.id

    @Before
    fun setUp(){
        viewModel = DetailViewModel()
        viewModel.setTvShowsId(tvId.toString())
        viewModel.setMoviesId(moviesId.toString())
    }

    @Test
    fun testGetDetailMoviesById() {
        val movies = viewModel.getDetailMoviesById()
        assertNotNull(movies)
        assertEquals(dummyMovies.name, movies.name)
        assertEquals(dummyMovies.desc, movies.desc)
        assertEquals(dummyMovies.poster, movies.poster)
        assertEquals(dummyMovies.img_preview, movies.img_preview)
    }

    @Test
    fun testGetDetailTvShowsById() {
        val tv = viewModel.getDetailTvShowsById()
        assertNotNull(tv)
        assertEquals(dummyTv.name, tv.name)
        assertEquals(dummyTv.desc, tv.desc)
        assertEquals(dummyTv.poster, tv.poster)
        assertEquals(dummyTv.img_preview, tv.img_preview)
    }
}