package com.makhalibagas.moviesaja.ui.tvshows

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.makhalibagas.moviesaja.adapter.MoviesAjaAdapter
import com.makhalibagas.moviesaja.adapter.TvShowsAjaAdapter
import com.makhalibagas.moviesaja.databinding.FragmentMoviesBinding
import com.makhalibagas.moviesaja.databinding.FragmentTvShowsBinding
import com.makhalibagas.moviesaja.viewmodel.MoviesAjaViewModel

class TVShowsFragment : Fragment() {

    private val viewModel : MoviesAjaViewModel by activityViewModels()
    private val binding : FragmentTvShowsBinding by lazy {
        FragmentTvShowsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter(){
        binding.rvTvshows.adapter = TvShowsAjaAdapter(viewModel.listTvShowsAja())
        binding.rvTvshows.set3DItem(true)
        binding.rvTvshows.setAlpha(true)
        binding.rvTvshows.setInfinite(true)

    }
}