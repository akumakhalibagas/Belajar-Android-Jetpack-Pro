package com.makhalibagas.moviesaja.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.makhalibagas.moviesaja.data.MoviesTvAja
import com.makhalibagas.moviesaja.data.source.MoviesAjaRepository
import com.makhalibagas.moviesaja.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel : DetailViewModel
    private val dummyMovies = DataDummy.listMoviesDummyAja()[0]
    private val dummyTv = DataDummy.listTvShowsDummyAja()[0]
    private val moviesId = dummyMovies.id
    private val tvId = dummyTv.id

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesAjaRepository: MoviesAjaRepository

    @Mock
    private lateinit var observer: Observer<MoviesTvAja>

    @Before
    fun setUp(){
        viewModel = DetailViewModel(moviesAjaRepository)
    }

    @Test
    fun testGetDetailMoviesById() {
        val movies = MutableLiveData<MoviesTvAja>()
        movies.value = dummyMovies

        `when`(moviesAjaRepository.getDetailMovies(moviesId!!)).thenReturn(movies)

        val moviesData = viewModel.getDetailMovies(moviesId).value as MoviesTvAja

        assertNotNull(movies)
        assertEquals(dummyMovies.name, moviesData.name)
        assertEquals(dummyMovies.desc, moviesData.desc)
        assertEquals(dummyMovies.poster, moviesData.poster)
        assertEquals(dummyMovies.backdrop, moviesData.backdrop)

        viewModel.getDetailMovies(moviesId).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun testGetDetailTvShowsById() {
        val tvAja = MutableLiveData<MoviesTvAja>()
        tvAja.value = dummyTv

        `when`(moviesAjaRepository.getDetailTv(tvId!!)).thenReturn(tvAja)

        val tvData = viewModel.getDetailTv(tvId).value as MoviesTvAja

        assertNotNull(tvAja)
        assertEquals(dummyTv.name, tvData.name)
        assertEquals(dummyTv.desc, tvData.desc)
        assertEquals(dummyTv.poster, tvData.poster)
        assertEquals(dummyTv.backdrop, tvData.backdrop)

        viewModel.getDetailTv(tvId).observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}