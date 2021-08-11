package com.makhalibagas.moviesaja.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.makhalibagas.moviesaja.adapter.MoviesAdapter
import com.makhalibagas.moviesaja.data.MoviesTvAja
import com.makhalibagas.moviesaja.databinding.FragmentMoviesBinding
import com.makhalibagas.moviesaja.ui.DetailActivity
import com.makhalibagas.moviesaja.utils.MoviesAjaCallback
import com.makhalibagas.moviesaja.viewmodel.MoviesAjaViewModel
import com.makhalibagas.moviesaja.viewmodel.ViewModelFactory
import java.util.*
import java.util.Collections.shuffle

class MoviesFragment : Fragment(), MoviesAjaCallback {

    private lateinit var viewModel : MoviesAjaViewModel
    private lateinit var listMoviesAja : List<MoviesTvAja>

    private val binding : FragmentMoviesBinding by lazy {
        FragmentMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[MoviesAjaViewModel::class.java]
        setupLiveObserver()
        onRefresh()
    }

    fun setupLiveObserver(){
        viewModel.getMovies().observe(viewLifecycleOwner, {
            listMoviesAja = it
            setupRv()
        })
    }

    fun setupRv(){
        binding.rvMovie.layoutManager = GridLayoutManager(context, 3)
        binding.rvMovie.adapter = MoviesAdapter(listMoviesAja, this@MoviesFragment)
    }

    fun onRefresh(){
        binding.refresh.setOnRefreshListener {
            shuffle()
            binding.refresh.isRefreshing = false
        }
    }

    fun shuffle(){
        shuffle(listMoviesAja,  Random(System.currentTimeMillis()))
        binding.rvMovie.adapter = MoviesAdapter(listMoviesAja, this@MoviesFragment)
    }

    override fun onClick(moviesTvAja: MoviesTvAja) {
        startActivity(Intent(context, DetailActivity::class.java)
                .putExtra("id", moviesTvAja.id)
                .putExtra("type", "movies")
        )
    }
}