package com.makhalibagas.moviesaja.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makhalibagas.moviesaja.R
import com.makhalibagas.moviesaja.data.MoviesTvAja
import com.makhalibagas.moviesaja.databinding.ItemsRowBinding
import com.makhalibagas.moviesaja.utils.MoviesAjaCallback

class MoviesAdapter(private val moviesTvAjaList: List<MoviesTvAja>, private val callback : MoviesAjaCallback) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val base_url_image = "https://image.tmdb.org/t/p/w185"

    inner class ViewHolder(val binding : ItemsRowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(moviesTv : MoviesTvAja){
            with(binding){
                Glide.with(itemView)
                        .load(base_url_image+moviesTv.poster)
                    .placeholder(R.color.red)
                        .into(poster)

                itemView.setOnClickListener {
                    callback.onClick(moviesTv)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviesAja = moviesTvAjaList[position]
        holder.bind(moviesAja)
    }

    override fun getItemCount(): Int = moviesTvAjaList.size

}