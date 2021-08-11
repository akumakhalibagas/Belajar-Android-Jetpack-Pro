package com.makhalibagas.moviesaja.data.source.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {


    //list popular movies and tv
    @GET("movie/popular")
    fun getPopularMovies(
            @Query("api_key") apiKey: String = "e8d81f73ff62ea119d3cb4b7eaddf71b"
    ) : Call<ResponseAja<MoviesAja>>

    @GET("tv/popular")
    fun getPopularTv(
            @Query("api_key") apiKey: String = "e8d81f73ff62ea119d3cb4b7eaddf71b"
    ) : Call<ResponseAja<TvShowsAja>>

    //detail movies and tv
    @GET("movie/{movie_id}")
    fun getDetailMovies(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String = "e8d81f73ff62ea119d3cb4b7eaddf71b"
    ) : Call<MoviesAja>

    @GET("movie/{tv_id}")
    fun getDetailTv(
            @Path("tv_id") tvId: Int,
            @Query("api_key") apiKey: String = "e8d81f73ff62ea119d3cb4b7eaddf71b"
    ) : Call<TvShowsAja>

}