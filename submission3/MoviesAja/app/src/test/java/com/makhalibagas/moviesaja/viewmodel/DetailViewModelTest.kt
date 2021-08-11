package com.makhalibagas.moviesaja.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.makhalibagas.moviesaja.data.source.MoviesAjaRepository
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
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

    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = DataDummy.listMoviesDummyAja()[0]
    private val dummyTv = DataDummy.listTvShowsDummyAja()[0]
    private val moviesId = dummyMovies.id
    private val tvId = dummyTv.id

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesAjaRepository: MoviesAjaRepository

    @Mock
    private lateinit var observer: Observer<MoviesAjaEntity>


    @Mock
    private lateinit var observerTv: Observer<TvShowsAjaEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(moviesAjaRepository)
    }

    @Test
    fun testGetDetailMoviesById() {
        val movies = MutableLiveData<MoviesAjaEntity>()
        movies.value = dummyMovies

        `when`(moviesAjaRepository.getDetailMovies(moviesId!!)).thenReturn(movies)

        val moviesData = viewModel.getDetailMovies(moviesId).value as MoviesAjaEntity

        assertNotNull(movies)
        assertEquals(dummyMovies.name, moviesData.name)
        assertEquals(dummyMovies.overview, moviesData.overview)
        assertEquals(dummyMovies.backdrop, moviesData.backdrop)

        viewModel.getDetailMovies(moviesId).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun testGetDetailTvShowsById() {
        val tvAja = MutableLiveData<TvShowsAjaEntity>()
        tvAja.value = dummyTv

        `when`(moviesAjaRepository.getDetailTv(tvId!!)).thenReturn(tvAja)

        val tvData = viewModel.getDetailTv(tvId).value as TvShowsAjaEntity

        assertNotNull(tvAja)
        assertEquals(dummyTv.name, tvData.name)
        assertEquals(dummyTv.overview, tvData.overview)
        assertEquals(dummyTv.backdrop, tvData.backdrop)

        viewModel.getDetailTv(tvId).observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)
    }
}