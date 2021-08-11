package com.makhalibagas.moviesaja.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.makhalibagas.moviesaja.adapter.MoviesAjaAdapter
import com.makhalibagas.moviesaja.adapter.TvShowsAjaAdapter
import com.makhalibagas.moviesaja.databinding.FragmentMoviesBinding
import com.makhalibagas.moviesaja.viewmodel.MoviesAjaViewModel

class MoviesFragment : Fragment() {

    private val viewModel : MoviesAjaViewModel by activityViewModels()
    private val binding : FragmentMoviesBinding by lazy {
        FragmentMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter(){
        binding.rvMovies.adapter = MoviesAjaAdapter(viewModel.listMoviesAja())
        binding.rvMovies.set3DItem(true)
        binding.rvMovies.setAlpha(true)
        binding.rvMovies.setInfinite(true)
    }
}