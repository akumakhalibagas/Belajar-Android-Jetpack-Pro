package com.makhalibagas.moviesaja.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.makhalibagas.moviesaja.LiveDataTestUtil
import com.makhalibagas.moviesaja.PagedListUtil
import com.makhalibagas.moviesaja.data.source.local.LocalDataSource
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
import com.makhalibagas.moviesaja.data.source.remote.RemoteDataSource
import com.makhalibagas.moviesaja.utils.DataDummy
import com.makhalibagas.moviesaja.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MoviesAjaRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val moviesAjaRepository = FakeMoviesAjaRepository(remote, local)

    private val listMoviesAja = DataDummy.listMoviesDummyAja()
    private val listTvAja = DataDummy.listTvShowsDummyAja()

    private val moviesAja = DataDummy.listMoviesDummyAja()[0]
    private val tvAja = DataDummy.listTvShowsDummyAja()[0]


    @Test
    fun testGetPopularMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesAjaEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        moviesAjaRepository.getPopularMovies()

        val moviesEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.listMoviesDummyAja()))
        verify(local).getMovies()
        assertNotNull(moviesEntities.data)
        assertEquals(listMoviesAja.size.toLong(), moviesEntities.data?.size?.toLong())

    }

    @Test
    fun testGetPopularTv() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsAjaEntity>
        `when`(local.getTv()).thenReturn(dataSourceFactory)
        moviesAjaRepository.getPopularTv()

        val tvEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.listTvShowsDummyAja()))
        verify(local).getTv()
        assertNotNull(tvEntities.data)
        assertEquals(listTvAja.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun testGetFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesAjaEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        moviesAjaRepository.getFavoriteMovies()

        val moviesFavoriteEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.listMoviesDummyAja()))
        verify(local).getFavoriteMovies()
        assertNotNull(moviesFavoriteEntities)
        assertEquals(listMoviesAja.size.toLong(), moviesFavoriteEntities.data?.size?.toLong())
    }

    @Test
    fun testGetFavoriteTv() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsAjaEntity>
        `when`(local.getFavoriteTv()).thenReturn(dataSourceFactory)
        moviesAjaRepository.getFavoriteTv()

        val tvFavoriteEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.listTvShowsDummyAja()))
        verify(local).getFavoriteTv()
        assertNotNull(tvFavoriteEntities)
        assertEquals(listTvAja.size.toLong(), tvFavoriteEntities.data?.size?.toLong())
    }

    @Test
    fun testGetDetailMovies() {
        val dummyMovies = MutableLiveData<MoviesAjaEntity>()
        dummyMovies.value = moviesAja
        `when`(local.getDetailMovies(moviesAja.moviesId)).thenReturn(dummyMovies)

        val moviesEntities =
            LiveDataTestUtil.getValue(moviesAjaRepository.getDetailMovies(moviesAja.moviesId))
        verify(local).getDetailMovies(moviesAja.moviesId)
        assertNotNull(moviesEntities)
        assertEquals(moviesAja.moviesId, moviesEntities.moviesId)
    }

    @Test
    fun testGetDetailTv() {
        val dummyTv = MutableLiveData<TvShowsAjaEntity>()
        dummyTv.value = tvAja
        `when`(local.getDetailTv(tvAja.tvId!!)).thenReturn(dummyTv)

        val tvEntities = LiveDataTestUtil.getValue(moviesAjaRepository.getDetailTv(tvAja.tvId!!))
        verify(local).getDetailTv(tvAja.tvId!!)
        assertNotNull(tvEntities)
        assertEquals(tvAja.tvId, tvEntities.tvId)
    }
}