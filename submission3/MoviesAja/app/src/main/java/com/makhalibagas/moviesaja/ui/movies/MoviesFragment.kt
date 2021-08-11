package com.makhalibagas.moviesaja.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.makhalibagas.moviesaja.adapter.MoviesAdapter
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.databinding.FragmentMoviesBinding
import com.makhalibagas.moviesaja.viewmodel.MoviesAjaViewModel
import com.makhalibagas.moviesaja.viewmodel.ViewModelFactory
import com.makhalibagas.moviesaja.vo.Status
import java.util.*
import java.util.Collections.shuffle

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesAjaViewModel
    private lateinit var listMoviesAja: PagedList<MoviesAjaEntity>
    private lateinit var adapter: MoviesAdapter

    private val binding: FragmentMoviesBinding by lazy {
        FragmentMoviesBinding.inflate(layoutInflater)
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
        viewModel =
            ViewModelProvider(
                this,
                ViewModelFactory.getInstance(context)
            )[MoviesAjaViewModel::class.java]
        adapter = MoviesAdapter()
        setupLiveObserver()
    }

    private fun setupLiveObserver() {
        viewModel.getMovies().observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        listMoviesAja = it.data!!
                        adapter.submitList(listMoviesAja)
                        setupRv()
                    }
                    Status.LOADING -> binding.progress.visibility = View.VISIBLE
                    Status.ERROR -> {
                        binding.progress.visibility = View.INVISIBLE
                        Toast.makeText(
                            context,
                            "Check your internet connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun setupRv() {
        binding.rvMovie.layoutManager = GridLayoutManager(context, 3)
        binding.rvMovie.adapter = adapter
    }
}