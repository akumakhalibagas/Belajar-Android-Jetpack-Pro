package com.makhalibagas.moviesaja.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makhalibagas.moviesaja.adapter.MoviesAdapter
import com.makhalibagas.moviesaja.adapter.TvAdapter
import com.makhalibagas.moviesaja.databinding.FragmentFavoriteBinding
import com.makhalibagas.moviesaja.viewmodel.MoviesAjaViewModel
import com.makhalibagas.moviesaja.viewmodel.ViewModelFactory

class FavoriteFragment : Fragment() {


    private lateinit var viewModel: MoviesAjaViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var adapterTv: TvAdapter

    private val binding: FragmentFavoriteBinding by lazy {
        FragmentFavoriteBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(context)
        )[MoviesAjaViewModel::class.java]

        adapter = MoviesAdapter()
        adapterTv = TvAdapter()
        binding.rvFavMovies.layoutManager = GridLayoutManager(context, 3)
        binding.rvFavTv.layoutManager = GridLayoutManager(context, 3)

        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, {
            adapter.submitList(it)
            binding.rvFavMovies.adapter = adapter
        })

        viewModel.getFavoriteTv().observe(viewLifecycleOwner, {
            adapterTv.submitList(it)
            binding.rvFavTv.adapter = adapterTv
        })
    }

}