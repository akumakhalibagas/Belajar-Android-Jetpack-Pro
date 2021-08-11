@file:Suppress("DEPRECATION")

package com.makhalibagas.moviesaja.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.makhalibagas.moviesaja.data.source.local.LocalDataSource
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
import com.makhalibagas.moviesaja.data.source.remote.*
import com.makhalibagas.moviesaja.data.source.remote.response.MoviesAja
import com.makhalibagas.moviesaja.data.source.remote.response.Person
import com.makhalibagas.moviesaja.data.source.remote.response.Trailer
import com.makhalibagas.moviesaja.data.source.remote.response.TvShowsAja
import com.makhalibagas.moviesaja.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeMoviesAjaRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MoviesAjaDataSource {

    override fun getPopularMovies(): LiveData<Resource<PagedList<MoviesAjaEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesAjaEntity>, List<MoviesAja>>() {
            override fun loadFromDB(): LiveData<PagedList<MoviesAjaEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesAjaEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MoviesAja>>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun saveCallResult(data: List<MoviesAja>) {
                val listMoviesAja = ArrayList<MoviesAjaEntity>()
                for (movies in data) {
                    val moviesAja = MoviesAjaEntity(
                        null,
                        movies.id,
                        movies.originalTitle,
                        movies.overview,
                        movies.voteCount,
                        movies.popularity,
                        movies.releaseDate,
                        movies.backdropPath,
                        movies.posterPath,
                        false
                    )

                    listMoviesAja.add(moviesAja)
                }

                localDataSource.insertMovies(listMoviesAja)
            }


        }.asLiveData()
    }

    override fun getPopularTv(): LiveData<Resource<PagedList<TvShowsAjaEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowsAjaEntity>, List<TvShowsAja>>() {
            override fun loadFromDB(): LiveData<PagedList<TvShowsAjaEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTv(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowsAjaEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowsAja>>> {
                return remoteDataSource.getPopularTv()
            }

            override fun saveCallResult(data: List<TvShowsAja>) {
                val tvListAja = ArrayList<TvShowsAjaEntity>()
                for (tv in data) {
                    val tvAja = TvShowsAjaEntity(
                        null,
                        tv.id,
                        tv.name,
                        tv.overview,
                        tv.voteCount,
                        tv.popularity,
                        tv.firstAirDate,
                        tv.backdropPath,
                        tv.posterPath,
                        false
                    )

                    tvListAja.add(tvAja)
                }

                localDataSource.insertTv(tvListAja)
            }


        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MoviesAjaEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<TvShowsAjaEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteTv(), config).build()
    }

    override fun setFavoriteMovies(moviesAjaEntity: MoviesAjaEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteMovies(moviesAjaEntity)
        }
    }

    override fun setFavoriteTv(tvShowsAjaEntity: TvShowsAjaEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteTv(tvShowsAjaEntity)
        }
    }

    override fun getDetailMovies(moviesId: Int): LiveData<MoviesAjaEntity> =
        localDataSource.getDetailMovies(moviesId)

    override fun getDetailTv(tvId: Int): LiveData<TvShowsAjaEntity> =
        localDataSource.getDetailTv(tvId)

    override fun getActorMovies(id: Int): LiveData<List<Person>> {
        val listPersonAja = MutableLiveData<List<Person>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getActorMovies(id, object : RemoteDataSource.loadActorMovies {
                override fun onActorMoviesReceived(listPerson: List<Person>) {
                    listPersonAja.postValue(listPerson)
                }
            })
        }
        return listPersonAja
    }

    override fun getActorTv(id: Int): LiveData<List<Person>> {
        val listPersonAja = MutableLiveData<List<Person>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getActorTv(id, object : RemoteDataSource.loadActorTv {
                override fun onActorTvReceived(listPerson: List<Person>) {
                    listPersonAja.postValue(listPerson)
                }
            })
        }
        return listPersonAja
    }

    override fun getTrailer(id: Int): LiveData<List<Trailer>> {
        val listTrailerAja = MutableLiveData<List<Trailer>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTrailer(id, object : RemoteDataSource.loadTrailer {
                override fun onTrailerReceived(listTrailer: List<Trailer>) {
                    listTrailerAja.postValue(listTrailer)
                }

            })
        }
        return listTrailerAja
    }

    override fun getTrailerTv(id: Int): LiveData<List<Trailer>> {
        val listTrailerAja = MutableLiveData<List<Trailer>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTrailerTv(id, object : RemoteDataSource.loadTrailerTv {
                override fun onTrailerTvReceived(listTrailer: List<Trailer>) {
                    listTrailerAja.postValue(listTrailer)
                }

            })
        }

        return listTrailerAja
    }

}