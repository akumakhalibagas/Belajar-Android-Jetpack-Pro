package com.makhalibagas.moviesaja.viewmodel

import androidx.lifecycle.ViewModel
import com.makhalibagas.moviesaja.data.MoviesAja
import com.makhalibagas.moviesaja.utils.DataDummy

class DetailViewModel : ViewModel(){

    private lateinit var moviesId: String
    private lateinit var tvshowsId: String

    fun listMoviesAja() : List<MoviesAja> = DataDummy.moviesAjaList()
    fun listTvShowsAja() : List<MoviesAja> = DataDummy.tvShowsAjaList()

    fun setMoviesId(moviesId : String){
        this.moviesId = moviesId
    }

    fun setTvShowsId(tvshowsId : String){
        this.tvshowsId = tvshowsId
    }

    fun getDetailMoviesById() : MoviesAja{
        lateinit var result : MoviesAja
        val listMoviesAja = listMoviesAja()
        for (movies in listMoviesAja){
            if (movies.id.toString().equals(moviesId)){
                result = movies
                break
            }
        }

        return result
    }

    fun getDetailTvShowsById() : MoviesAja{
        lateinit var result : MoviesAja
        val listMoviesAja = listTvShowsAja()
        for (tvshows in listMoviesAja){
            if (tvshows.id.toString().equals(tvshowsId)){
                result = tvshows
                break
            }
        }

        return result
    }
}