package com.makhalibagas.moviesaja.viewmodel

import androidx.lifecycle.ViewModel
import com.makhalibagas.moviesaja.data.MoviesAja
import com.makhalibagas.moviesaja.utils.DataDummy

class MoviesAjaViewModel : ViewModel() {

    fun listMoviesAja() : List<MoviesAja> = DataDummy.moviesAjaList()

    fun listTvShowsAja() : List<MoviesAja> = DataDummy.tvShowsAjaList()

}