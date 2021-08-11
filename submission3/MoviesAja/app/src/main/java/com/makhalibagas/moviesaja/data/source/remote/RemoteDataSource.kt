package com.makhalibagas.moviesaja.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.makhalibagas.moviesaja.data.source.remote.response.MoviesAja
import com.makhalibagas.moviesaja.data.source.remote.response.Person
import com.makhalibagas.moviesaja.data.source.remote.response.Trailer
import com.makhalibagas.moviesaja.data.source.remote.response.TvShowsAja
import com.makhalibagas.moviesaja.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getPopularMovies(): LiveData<ApiResponse<List<MoviesAja>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MoviesAja>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val movies = tmdbServiceApi().getPopularMovies().await()
                result.postValue(ApiResponse.success(movies.results!!))

            } catch (e: IOException) {
                e.printStackTrace()
                result.postValue(
                    ApiResponse.error(e.message.toString(), mutableListOf())
                )
            }
        }

        EspressoIdlingResource.decrement()
        return result
    }

    fun getPopularTv(): LiveData<ApiResponse<List<TvShowsAja>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvShowsAja>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val tv = tmdbServiceApi().getPopularTv().await()
                result.postValue(ApiResponse.success(tv.results!!))

            } catch (e: IOException) {
                e.printStackTrace()
                result.postValue(
                    ApiResponse.error(e.message.toString(), mutableListOf())
                )
            }
        }

        EspressoIdlingResource.decrement()
        return result
    }

    suspend fun getActorMovies(id: Int, callback: loadActorMovies) {
        EspressoIdlingResource.increment()
        tmdbServiceApi().getActorMovies(id).await().cast?.let {
            callback.onActorMoviesReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getActorTv(id: Int, callback: loadActorTv) {
        EspressoIdlingResource.increment()
        tmdbServiceApi().getActorTv(id).await().cast?.let {
            callback.onActorTvReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTrailerTv(id: Int, callback: loadTrailerTv) {
        EspressoIdlingResource.increment()
        tmdbServiceApi().getTrailerTv(id).await().results?.let {
            callback.onTrailerTvReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTrailer(id: Int, callback: loadTrailer) {
        EspressoIdlingResource.increment()
        tmdbServiceApi().getTrailerMovies(id).await().results?.let {
            callback.onTrailerReceived(it)
            EspressoIdlingResource.decrement()
        }
    }


    interface loadActorMovies {
        fun onActorMoviesReceived(listPerson: List<Person>)
    }

    interface loadActorTv {
        fun onActorTvReceived(listPerson: List<Person>)
    }

    interface loadTrailer {
        fun onTrailerReceived(listTrailer: List<Trailer>)
    }

    interface loadTrailerTv {
        fun onTrailerTvReceived(listTrailer: List<Trailer>)
    }
}
