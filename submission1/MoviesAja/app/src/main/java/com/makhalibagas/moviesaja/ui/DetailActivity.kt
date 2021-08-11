package com.makhalibagas.moviesaja.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.makhalibagas.moviesaja.R
import com.makhalibagas.moviesaja.data.MoviesAja
import com.makhalibagas.moviesaja.databinding.ActivityDetailBinding
import com.makhalibagas.moviesaja.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel : DetailViewModel by viewModels()
    private lateinit var result : MoviesAja

    private val binding : ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.red)
        setContentView(binding.root)


        val id = intent.getStringExtra("moviesAja")
        val type = intent.getStringExtra("type")


        if (type.equals("typeMovies")){

            viewModel.setMoviesId(id!!)
            result = viewModel.getDetailMoviesById()
            binding.tvName.text = result.name
            binding.tvOverview.text = result.desc
            Glide.with(this).load(result.poster).into(binding.poster)

        }else if (type.equals("typeTvShows")){

            viewModel.setTvShowsId(id!!)
            result = viewModel.getDetailTvShowsById()
            binding.tvName.text = result.name
            binding.tvOverview.text = result.desc
            Glide.with(this).load(result.poster).into(binding.poster)
        }

    }

}