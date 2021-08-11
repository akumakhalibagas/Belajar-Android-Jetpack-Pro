package com.makhalibagas.moviesaja.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.makhalibagas.moviesaja.data.source.MoviesAjaRepository
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
import com.makhalibagas.moviesaja.data.source.remote.response.Person
import com.makhalibagas.moviesaja.data.source.remote.response.Trailer

class DetailViewModel(private val moviesAjaRepository: MoviesAjaRepository) : ViewModel() {

    fun getDetailMovies(id: Int): LiveData<MoviesAjaEntity> =
        moviesAjaRepository.getDetailMovies(id)

    fun getDetailTv(id: Int): LiveData<TvShowsAjaEntity> = moviesAjaRepository.getDetailTv(id)

    fun setFavoriteMovies(moviesAjaEntity: MoviesAjaEntity) {
        moviesAjaRepository.setFavoriteMovies(moviesAjaEntity)
    }

    fun setFavoriteTv(tvShowsAjaEntity: TvShowsAjaEntity) {
        moviesAjaRepository.setFavoriteTv(tvShowsAjaEntity)
    }

    fun getActor(id: Int): LiveData<List<Person>> = moviesAjaRepository.getActorMovies(id)
    fun getActorTv(id: Int): LiveData<List<Person>> = moviesAjaRepository.getActorTv(id)

    fun getTrailer(id: Int): LiveData<List<Trailer>> = moviesAjaRepository.getTrailer(id)
    fun getTrailerTv(id: Int): LiveData<List<Trailer>> = moviesAjaRepository.getTrailerTv(id)
}