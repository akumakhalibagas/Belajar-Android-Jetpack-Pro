package com.makhalibagas.moviesaja.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.makhalibagas.moviesaja.data.MoviesTvAja
import com.makhalibagas.moviesaja.data.source.MoviesAjaRepository

class MoviesAjaViewModel(private val moviesAjaRepository: MoviesAjaRepository) : ViewModel() {

    fun getMovies() : LiveData<List<MoviesTvAja>> = moviesAjaRepository.getPopularMovies()

    fun getTv() : LiveData<List<MoviesTvAja>> = moviesAjaRepository.getPopularTv()

}