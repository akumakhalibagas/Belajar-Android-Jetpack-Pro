@file:Suppress("DEPRECATION")

package com.makhalibagas.moviesaja.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.makhalibagas.moviesaja.data.MoviesTvAja
import com.makhalibagas.moviesaja.data.source.MoviesAjaRepository
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
import com.makhalibagas.moviesaja.vo.Resource

class MoviesAjaViewModel(private val moviesAjaRepository: MoviesAjaRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MoviesAjaEntity>>> =
        moviesAjaRepository.getPopularMovies()

    fun getTv(): LiveData<Resource<PagedList<TvShowsAjaEntity>>> =
        moviesAjaRepository.getPopularTv()


    fun getFavoriteMovies(): LiveData<PagedList<MoviesAjaEntity>> =
        moviesAjaRepository.getFavoriteMovies()

    fun getFavoriteTv(): LiveData<PagedList<TvShowsAjaEntity>> = moviesAjaRepository.getFavoriteTv()

}