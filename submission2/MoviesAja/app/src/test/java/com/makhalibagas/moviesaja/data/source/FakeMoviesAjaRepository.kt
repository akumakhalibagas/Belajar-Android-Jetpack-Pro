package com.makhalibagas.moviesaja.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.makhalibagas.moviesaja.data.MoviesTvAja
import com.makhalibagas.moviesaja.data.source.remote.MoviesAja
import com.makhalibagas.moviesaja.data.source.remote.RemoteDataSource
import com.makhalibagas.moviesaja.data.source.remote.TvShowsAja
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeMoviesAjaRepository(private val remoteDataSource: RemoteDataSource) : MoviesAjaDataSource{

    override fun getPopularMovies(): LiveData<List<MoviesTvAja>> {
        val listMoviesAja = MutableLiveData<List<MoviesTvAja>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularMovies(object : RemoteDataSource.loadPopularMoviesCallback{
                override fun onPopularMoviesReceived(moviesResponse: List<MoviesAja>) {
                    val moviesListAja = ArrayList<MoviesTvAja>()
                    for (response in moviesResponse){
                        val movies = MoviesTvAja(
                                response.id,
                                response.name,
                                response.desc,
                                response.poster,
                                response.img_preview
                        )
                        moviesListAja.add(movies)
                    }
                    listMoviesAja.postValue(moviesListAja)
                }
            })
        }

        return listMoviesAja
    }

    override fun getPopularTv(): LiveData<List<MoviesTvAja>> {
        val listTvAja = MutableLiveData<List<MoviesTvAja>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularTv(object : RemoteDataSource.loadPopularTvCallback{
                override fun onPopularTvReceived(tvResponse: List<TvShowsAja>) {
                    val tvListAja = ArrayList<MoviesTvAja>()
                    for (result in tvResponse){

                        val movies = MoviesTvAja(
                                result.id,
                                result.name,
                                result.desc,
                                result.poster,
                                result.img_preview
                        )
                        tvListAja.add(movies)

                    }

                    listTvAja.postValue(tvListAja)
                }

            })
        }

        return listTvAja
    }

    override fun getDetailMovies(moviesId: Int): LiveData<MoviesTvAja> {

        val movies = MutableLiveData<MoviesTvAja>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailMovies(moviesId, object : RemoteDataSource.loadDetailMovies{
                override fun onDetailMoviesReceived(moviesAja: MoviesAja) {
                    val movie = MoviesTvAja(
                            moviesAja.id,
                            moviesAja.name,
                            moviesAja.desc,
                            moviesAja.poster,
                            moviesAja.img_preview
                    )

                    movies.postValue(movie)
                }
            })
        }

        return movies
    }

    override fun getDetailTv(tvId: Int): LiveData<MoviesTvAja> {

        val tvshows = MutableLiveData<MoviesTvAja>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailTv(tvId, object : RemoteDataSource.loadDetailTv{
                override fun onDetailTvReceived(tvShowsAja: TvShowsAja) {
                    val tv = MoviesTvAja(
                            tvShowsAja.id,
                            tvShowsAja.name,
                            tvShowsAja.desc,
                            tvShowsAja.poster,
                            tvShowsAja.img_preview
                    )

                    tvshows.postValue(tv)
                }
            })
        }

        return tvshows
    }

}
