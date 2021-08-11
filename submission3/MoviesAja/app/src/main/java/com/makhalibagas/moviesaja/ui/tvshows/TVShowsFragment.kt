package com.makhalibagas.moviesaja.ui.tvshows

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.makhalibagas.moviesaja.adapter.TvAdapter
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
import com.makhalibagas.moviesaja.databinding.FragmentTvShowsBinding
import com.makhalibagas.moviesaja.viewmodel.MoviesAjaViewModel
import com.makhalibagas.moviesaja.viewmodel.ViewModelFactory
import com.makhalibagas.moviesaja.vo.Status
import java.util.*
import java.util.Collections.shuffle

class TVShowsFragment : Fragment() {

    private lateinit var viewModel: MoviesAjaViewModel
    private lateinit var listTvAja: PagedList<TvShowsAjaEntity>
    private lateinit var adapter: TvAdapter

    private val binding: FragmentTvShowsBinding by lazy {
        FragmentTvShowsBinding.inflate(layoutInflater)
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
        adapter = TvAdapter()
        setupLiveObserver()
    }

    private fun setupLiveObserver() {
        viewModel.getTv().observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        listTvAja = it.data!!
                        adapter.submitList(listTvAja)
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
        binding.rvTv.layoutManager = GridLayoutManager(context, 3)
        binding.rvTv.adapter = adapter
    }
}