package com.makhalibagas.moviesaja.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
import com.makhalibagas.moviesaja.data.source.remote.response.Person
import com.makhalibagas.moviesaja.data.source.remote.response.Trailer
import com.makhalibagas.moviesaja.vo.Resource

interface MoviesAjaDataSource {

    fun getPopularMovies(): LiveData<Resource<PagedList<MoviesAjaEntity>>>
    fun getPopularTv(): LiveData<Resource<PagedList<TvShowsAjaEntity>>>

    fun getFavoriteMovies(): LiveData<PagedList<MoviesAjaEntity>>
    fun getFavoriteTv(): LiveData<PagedList<TvShowsAjaEntity>>

    fun setFavoriteMovies(moviesAjaEntity: MoviesAjaEntity)
    fun setFavoriteTv(tvShowsAjaEntity: TvShowsAjaEntity)

    fun getDetailMovies(moviesId: Int): LiveData<MoviesAjaEntity>
    fun getDetailTv(tvId: Int): LiveData<TvShowsAjaEntity>

    fun getActorMovies(id: Int): LiveData<List<Person>>
    fun getActorTv(id: Int): LiveData<List<Person>>

    fun getTrailer(id: Int): LiveData<List<Trailer>>
    fun getTrailerTv(id: Int): LiveData<List<Trailer>>
}