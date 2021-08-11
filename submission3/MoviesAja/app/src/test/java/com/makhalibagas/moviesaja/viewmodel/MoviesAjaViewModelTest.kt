package com.makhalibagas.moviesaja.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.makhalibagas.moviesaja.data.source.MoviesAjaRepository
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
import com.makhalibagas.moviesaja.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesAjaViewModelTest {

    private lateinit var viewModel: MoviesAjaViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesAjaRepository: MoviesAjaRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesAjaEntity>>>

    @Mock
    private lateinit var observerTv: Observer<Resource<PagedList<TvShowsAjaEntity>>>

    @Mock
    private lateinit var observerFav: Observer<PagedList<MoviesAjaEntity>>

    @Mock
    private lateinit var observerFavTv: Observer<PagedList<TvShowsAjaEntity>>

    @Mock
    private lateinit var listTvAja: PagedList<TvShowsAjaEntity>

    @Mock
    private lateinit var listMoviesAja: PagedList<MoviesAjaEntity>


    @Before
    fun setUp() {
        viewModel = MoviesAjaViewModel(moviesAjaRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyMovies = Resource.success(listMoviesAja)
        `when`(dummyMovies.data?.size).thenReturn(10)
        val movies = MutableLiveData<Resource<PagedList<MoviesAjaEntity>>>()
        movies.value = dummyMovies

        `when`(moviesAjaRepository.getPopularMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getMovies().value?.data
        verify(moviesAjaRepository).getPopularMovies()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun testGetTv() {
        val dummyTv = Resource.success(listTvAja)
        `when`(dummyTv.data?.size).thenReturn(10)
        val tv = MutableLiveData<Resource<PagedList<TvShowsAjaEntity>>>()
        tv.value = dummyTv

        `when`(moviesAjaRepository.getPopularTv()).thenReturn(tv)
        val tvEntities = viewModel.getTv().value?.data
        verify(moviesAjaRepository).getPopularTv()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        viewModel.getTv().observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)
    }

    @Test
    fun testGetFavoriteMovies() {
        val dummyFavoriteMovies = listMoviesAja
        `when`(dummyFavoriteMovies.size).thenReturn(10)
        val movies = MutableLiveData<PagedList<MoviesAjaEntity>>()
        movies.value = dummyFavoriteMovies

        `when`(moviesAjaRepository.getFavoriteMovies()).thenReturn(movies)
        val tvEntities = viewModel.getFavoriteMovies().value
        verify(moviesAjaRepository).getFavoriteMovies()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        viewModel.getFavoriteMovies().observeForever(observerFav)
        verify(observerFav).onChanged(dummyFavoriteMovies)
    }

    @Test
    fun testGetFavoriteTv() {
        val dummyFavoriteTv = listTvAja
        `when`(dummyFavoriteTv.size).thenReturn(10)
        val tv = MutableLiveData<PagedList<TvShowsAjaEntity>>()
        tv.value = dummyFavoriteTv

        `when`(moviesAjaRepository.getFavoriteTv()).thenReturn(tv)
        val tvEntities = viewModel.getFavoriteTv().value
        verify(moviesAjaRepository).getFavoriteTv()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        viewModel.getFavoriteTv().observeForever(observerFavTv)
        verify(observerFavTv).onChanged(dummyFavoriteTv)
    }
}