package com.makhalibagas.moviesaja.ui.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.makhalibagas.moviesaja.adapter.MoviesAdapter
import com.makhalibagas.moviesaja.data.MoviesTvAja
import com.makhalibagas.moviesaja.databinding.FragmentTvShowsBinding
import com.makhalibagas.moviesaja.ui.DetailActivity
import com.makhalibagas.moviesaja.utils.MoviesAjaCallback
import com.makhalibagas.moviesaja.viewmodel.MoviesAjaViewModel
import com.makhalibagas.moviesaja.viewmodel.ViewModelFactory
import java.util.*
import java.util.Collections.shuffle

class TVShowsFragment : Fragment(), MoviesAjaCallback {

    private lateinit var viewModel : MoviesAjaViewModel
    private lateinit var listTvAja : List<MoviesTvAja>

    private val binding : FragmentTvShowsBinding by lazy {
        FragmentTvShowsBinding.inflate(layoutInflater)
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
        viewModel.getTv().observe(viewLifecycleOwner, {
            listTvAja = it
            setupRv()
        })
    }

    fun setupRv(){
        binding.rvTv.layoutManager = GridLayoutManager(context, 3)
        binding.rvTv.adapter = MoviesAdapter(listTvAja,this@TVShowsFragment)
    }

    fun onRefresh(){
        binding.refresh.setOnRefreshListener {
            shuffle()
            binding.refresh.isRefreshing = false
        }
    }

    fun shuffle(){
        shuffle(listTvAja, Random(System.currentTimeMillis()))
        binding.rvTv.adapter = MoviesAdapter(listTvAja, this@TVShowsFragment)
    }

    override fun onClick(moviesTvAja: MoviesTvAja) {
        startActivity(Intent(context, DetailActivity::class.java)
                .putExtra("id", moviesTvAja.id)
                .putExtra("type", "tv")
        )
    }
}