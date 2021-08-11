package com.makhalibagas.moviesaja.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.makhalibagas.moviesaja.LiveDataTestUtil
import com.makhalibagas.moviesaja.data.source.remote.RemoteDataSource
import com.makhalibagas.moviesaja.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Rule
import com.nhaarman.mockitokotlin2.any
import org.mockito.Mockito.*

class MoviesAjaRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val moviesAjaRepository = FakeMoviesAjaRepository(remote)

    private val listMoviesResponse = DataDummy.generateDataMoviesDummyResponse()
    private val moviesId = listMoviesResponse[0].id
    private val listTvResponse = DataDummy.generateDataTvShowDummyResponse()
    private val tvId = listTvResponse[0].id
    private val moviesResponse = DataDummy.generateDataMoviesDummyResponse()[0]
    private val tvResponse = DataDummy.generateDataTvShowDummyResponse()[0]




    @Test
    fun getPopularMovies() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as RemoteDataSource.loadPopularMoviesCallback).onPopularMoviesReceived(listMoviesResponse)
                null
            }.`when`(remote).getPopularMovies(any())
        }

        val moviesAja = LiveDataTestUtil.getValue(moviesAjaRepository.getPopularMovies())

        runBlocking {
            verify(remote).getPopularMovies(any())
        }

        assertNotNull(moviesAja)
        assertEquals(listMoviesResponse.size.toLong(), moviesAja.size.toLong())
    }

    @Test
    fun getPopularTv() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as RemoteDataSource.loadPopularTvCallback).onPopularTvReceived(listTvResponse)
                null
            }.`when`(remote).getPopularTv(any())
        }

        val tvAja = LiveDataTestUtil.getValue(moviesAjaRepository.getPopularTv())

        runBlocking {
            verify(remote).getPopularTv(any())
        }

        assertNotNull(tvAja)
        assertEquals(listTvResponse.size.toLong(), tvAja.size.toLong())
    }

    @Test
    fun getDetailMovies() {
        runBlocking {
            doAnswer {
                (it.arguments[1] as RemoteDataSource.loadDetailMovies).onDetailMoviesReceived(moviesResponse)
                null
            }.`when`(remote).getDetailMovies(eq(moviesId), any())
        }

        val moviesAja = LiveDataTestUtil.getValue(moviesAjaRepository.getDetailMovies(moviesId))

        runBlocking {
            verify(remote).getDetailMovies(eq(moviesId), any())
        }

        assertNotNull(moviesAja)
        assertEquals(moviesResponse.id, moviesAja.id)
    }

    @Test
    fun getDetailTv() {
        runBlocking {
            doAnswer {
                (it.arguments[1] as RemoteDataSource.loadDetailTv).onDetailTvReceived(tvResponse)
                null
            }.`when`(remote).getDetailTv(eq(tvId), any())
        }

        val tvAja = LiveDataTestUtil.getValue(moviesAjaRepository.getDetailTv(tvId))

        runBlocking {
            verify(remote).getDetailTv(eq(tvId), any())
        }

        assertNotNull(tvAja)
        assertEquals(tvResponse.id, tvAja.id)
    }
}