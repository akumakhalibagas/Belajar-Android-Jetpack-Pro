package com.makhalibagas.moviesaja.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.makhalibagas.moviesaja.R
import com.makhalibagas.moviesaja.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private val binding : FragmentFavoriteBinding by lazy {
        FragmentFavoriteBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

}