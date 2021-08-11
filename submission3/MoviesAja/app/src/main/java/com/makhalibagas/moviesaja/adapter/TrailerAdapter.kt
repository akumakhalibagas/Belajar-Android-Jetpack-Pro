package com.makhalibagas.moviesaja.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makhalibagas.moviesaja.R
import com.makhalibagas.moviesaja.data.source.remote.response.Trailer
import com.makhalibagas.moviesaja.databinding.ItemsVideoBinding

class TrailerAdapter(private val trailerListAja: List<Trailer>) :
    RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    //https://img.youtube.com/vi/sBjNRLiLO5Y/0.jpg
    //https://www.youtube.com/watch?v=sBjNRLiLO5Y

    inner class ViewHolder(val binding: ItemsVideoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(moviesTv: Trailer) {
            Glide.with(itemView)
                .load("https://img.youtube.com/vi/${moviesTv.key}/0.jpg")
                .placeholder(R.color.red)
                .into(binding.thumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviesAja = trailerListAja[position]
        holder.bind(moviesAja)
    }

    override fun getItemCount(): Int = trailerListAja.size

}