package com.makhalibagas.moviesaja.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity

@Dao
interface MoviesAjaDao {

    @Query("SELECT * FROM tb_movies")
    fun getMovies(): DataSource.Factory<Int, MoviesAjaEntity>

    @Query("SELECT * FROM tb_movies WHERE isFavorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MoviesAjaEntity>

    @Query("SELECT * FROM tb_movies WHERE moviesId = :moviesId")
    fun getDetailMovies(moviesId: Int): LiveData<MoviesAjaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MoviesAjaEntity::class)
    fun insertMovies(moviesAja: List<MoviesAjaEntity>)

    @Update(entity = MoviesAjaEntity::class)
    fun updateMovies(movies: MoviesAjaEntity)


    @Query("SELECT * FROM tb_tvshow")
    fun getTv(): DataSource.Factory<Int, TvShowsAjaEntity>

    @Query("SELECT * FROM tb_tvshow WHERE isFavorite = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, TvShowsAjaEntity>

    @Query("SELECT * FROM tb_tvshow WHERE tvId = :tvId")
    fun getDetailTv(tvId: Int): LiveData<TvShowsAjaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowsAjaEntity::class)
    fun insertTv(Tv: List<TvShowsAjaEntity>)

    @Update(entity = TvShowsAjaEntity::class)
    fun updateTv(Tv: TvShowsAjaEntity)
}