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
class MoviesTvAjaViewModelTest {

    private val dummyMoviesAja = DataDummy.listMoviesDummyAja()
    private val dummyTvAja = DataDummy.listTvShowsDummyAja()

    private lateinit var viewmodel : MoviesAjaViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesAjaRepository: MoviesAjaRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesTvAja>>

    @Before
    fun setup(){
        viewmodel = MoviesAjaViewModel(moviesAjaRepository)
    }

    @Test
    fun testListMoviesAja() {
        val moviesAja = MutableLiveData<List<MoviesTvAja>>()
        moviesAja.value = dummyMoviesAja
        `when`(moviesAjaRepository.getPopularMovies()).thenReturn(moviesAja)

        val listMoviesAja = viewmodel.getMovies().value

        verify(moviesAjaRepository).getPopularMovies()
        assertNotNull(listMoviesAja)
        assertEquals(10, listMoviesAja?.size)

        viewmodel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMoviesAja)
    }

    @Test
    fun testListTvShowsAja() {
        val tvAja = MutableLiveData<List<MoviesTvAja>>()
        tvAja.value = dummyTvAja
        `when`(moviesAjaRepository.getPopularTv()).thenReturn(tvAja)

        val listTvsAja = viewmodel.getTv().value

        verify(moviesAjaRepository).getPopularTv()
        assertNotNull(listTvsAja)
        assertEquals(10, listTvsAja?.size)

        viewmodel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTvAja)
    }
}