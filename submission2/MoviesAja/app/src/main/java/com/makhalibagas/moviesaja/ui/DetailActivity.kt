package com.makhalibagas.moviesaja.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.makhalibagas.moviesaja.R
import com.makhalibagas.moviesaja.data.MoviesTvAja
import com.makhalibagas.moviesaja.databinding.ActivityDetailBinding
import com.makhalibagas.moviesaja.viewmodel.DetailViewModel
import com.makhalibagas.moviesaja.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel : DetailViewModel
    private val base_url_poster = "https://image.tmdb.org/t/p/w185"
    private val base_url_backdrop = "https://image.tmdb.org/t/p/w780"

    private val binding : ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.red)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this@DetailActivity, ViewModelFactory.getInstance())[DetailViewModel::class.java]

        val id = intent.getIntExtra("id",0)
        val type = intent.getStringExtra("type")

        if (type.equals("tv")){
            viewModel.getDetailTv(id).observe(this, {
                showData(it)
            })
        }else if (type.equals("movies")){
            viewModel.getDetailMovies(id).observe(this, {
                showData(it)
            })
        }
    }

    fun showData(moviesTvAja: MoviesTvAja){
        binding.tvName.text = moviesTvAja.name
        binding.tvOverview.text = moviesTvAja.desc
        Glide.with(this).load(base_url_poster+moviesTvAja.poster).into(binding.poster)
        Glide.with(this).load(base_url_backdrop+moviesTvAja.backdrop).into(binding.backdrop)
    }

}