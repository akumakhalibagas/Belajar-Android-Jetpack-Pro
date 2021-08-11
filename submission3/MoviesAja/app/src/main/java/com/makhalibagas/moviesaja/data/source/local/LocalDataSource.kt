package com.makhalibagas.moviesaja.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
import com.makhalibagas.moviesaja.data.source.local.room.MoviesAjaDao

class LocalDataSource private constructor(private val moviesAjaDao: MoviesAjaDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(moviesAjaDao: MoviesAjaDao): LocalDataSource =
            instance ?: LocalDataSource(moviesAjaDao)
    }


    fun getMovies(): DataSource.Factory<Int, MoviesAjaEntity> = moviesAjaDao.getMovies()
    fun getFavoriteMovies(): DataSource.Factory<Int, MoviesAjaEntity> =
        moviesAjaDao.getFavoriteMovies()

    fun getDetailMovies(id: Int): LiveData<MoviesAjaEntity> = moviesAjaDao.getDetailMovies(id)
    fun insertMovies(movies: List<MoviesAjaEntity>) = moviesAjaDao.insertMovies(movies)
    fun setFavoriteMovies(moviesAjaEntity: MoviesAjaEntity) {
        moviesAjaEntity.isFavorite = !moviesAjaEntity.isFavorite
        moviesAjaDao.updateMovies(moviesAjaEntity)
    }


    fun getTv(): DataSource.Factory<Int, TvShowsAjaEntity> = moviesAjaDao.getTv()
    fun getFavoriteTv(): DataSource.Factory<Int, TvShowsAjaEntity> = moviesAjaDao.getFavoriteTv()
    fun getDetailTv(id: Int): LiveData<TvShowsAjaEntity> = moviesAjaDao.getDetailTv(id)
    fun insertTv(movies: List<TvShowsAjaEntity>) = moviesAjaDao.insertTv(movies)
    fun setFavoriteTv(tv: TvShowsAjaEntity) {
        tv.isFavorite = !tv.isFavorite
        moviesAjaDao.updateTv(tv)
    }


}