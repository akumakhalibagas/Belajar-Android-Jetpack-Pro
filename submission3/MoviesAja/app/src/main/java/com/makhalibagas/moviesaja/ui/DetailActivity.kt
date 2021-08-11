package com.makhalibagas.moviesaja.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.makhalibagas.moviesaja.adapter.PersonAdapter
import com.makhalibagas.moviesaja.adapter.TrailerAdapter
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
import com.makhalibagas.moviesaja.data.source.remote.response.Person
import com.makhalibagas.moviesaja.data.source.remote.response.Trailer
import com.makhalibagas.moviesaja.databinding.ActivityDetailBinding
import com.makhalibagas.moviesaja.viewmodel.DetailViewModel
import com.makhalibagas.moviesaja.viewmodel.ViewModelFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    companion object {
        const val base_url_backdrop = "https://image.tmdb.org/t/p/w780"
    }


    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this@DetailActivity,
            ViewModelFactory.getInstance(this)
        )[DetailViewModel::class.java]

        val id = intent.getIntExtra("id", 0)
        val type = intent.getStringExtra("type")

        if (type.equals("tv")) {
            observerTv(id)

        } else if (type.equals("movies")) {
            observerMovies(id)

        }
    }

    private fun setFavorite(movies: MoviesAjaEntity?, tv: TvShowsAjaEntity?) {
        if (movies != null) {
            if (movies.isFavorite) {
                showSnackBar("${movies.name} berhasil dihapus dari favorite")
            } else {
                showSnackBar("${movies.name} berhasil ditambahkan ke favorite")
            }

            viewModel.setFavoriteMovies(movies)

        } else if (tv != null) {
            if (tv.isFavorite) {
                showSnackBar("${tv.name} berhasil dihapus dari favorite")
            } else {
                showSnackBar("${tv.name} berhasil ditambahkan ke favorite")
            }

            viewModel.setFavoriteTv(tv)

        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun status(status: Boolean) {
        if (status) {
            binding.favFull.visibility = View.VISIBLE
            binding.fav.visibility = View.INVISIBLE
        } else {

            binding.favFull.visibility = View.INVISIBLE
            binding.fav.visibility = View.VISIBLE
        }
    }

    private fun observerMovies(id: Int) {
        viewModel.getDetailMovies(id).observe(this, {
            showData(it, null)
        })

        viewModel.getActor(id).observe(this, {
            setupRvPerson(it)
            Log.d("actorrrr", it.toString())
        })

        viewModel.getTrailer(id).observe(this, {
            Log.d("tailerr", it.toString())
            setupRvTrailer(it)
        })
    }

    private fun observerTv(id: Int) {
        viewModel.getDetailTv(id).observe(this, {
            showData(null, it)
        })

        viewModel.getActorTv(id).observe(this, {
            setupRvPerson(it)
        })

        viewModel.getTrailerTv(id).observe(this, {
            setupRvTrailer(it)
        })
    }

    @SuppressLint("SimpleDateFormat")
    fun showData(moviesAja: MoviesAjaEntity?, tv: TvShowsAjaEntity?) {

        val backdrop = moviesAja?.backdrop ?: tv?.backdrop
        val vote = moviesAja?.voteCount ?: tv?.voteCount
        val popularity = moviesAja?.popularity ?: tv?.popularity
        val release = moviesAja?.release ?: tv?.release
        val status = moviesAja?.isFavorite ?: tv?.isFavorite
        status(status!!)
        binding.fav.setOnClickListener {
            setFavorite(moviesAja, tv)
            //false maka jadi true
        }

        binding.favFull.setOnClickListener {
            setFavorite(moviesAja, tv)
            //true maka jadi false
        }

        binding.tvName.text = moviesAja?.name ?: tv?.name
        binding.tvOverview.text = moviesAja?.overview ?: tv?.overview
        binding.tvVote.text = vote.toString()
        binding.tvPopularity.text = popularity.toString()
        Glide.with(this).load(base_url_backdrop + backdrop).into(binding.backdrop)

        try {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(release!!)
            val formater = SimpleDateFormat("dd MMMM yyyy")
            val newDateFormat: String = formater.format(date!!)
            binding.tvRelease.text = newDateFormat
        } catch (e: ParseException) {
            e.printStackTrace()
        }

    }

    private fun setupRvPerson(it: List<Person>) {
        binding.rvPerson.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvPerson.adapter = PersonAdapter(it)
    }

    private fun setupRvTrailer(it: List<Trailer>) {
        binding.rvTrailer.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvTrailer.adapter = TrailerAdapter(it)
    }

}