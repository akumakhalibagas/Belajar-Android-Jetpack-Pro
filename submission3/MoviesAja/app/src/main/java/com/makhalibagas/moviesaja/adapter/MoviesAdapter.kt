package com.makhalibagas.moviesaja.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makhalibagas.moviesaja.R
import com.makhalibagas.moviesaja.data.source.local.entity.MoviesAjaEntity
import com.makhalibagas.moviesaja.databinding.ItemsRowBinding
import com.makhalibagas.moviesaja.ui.DetailActivity

class MoviesAdapter : PagedListAdapter<MoviesAjaEntity, MoviesAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        const val base_url_image = "https://image.tmdb.org/t/p/w185"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesAjaEntity>() {
            override fun areItemsTheSame(
                oldItem: MoviesAjaEntity,
                newItem: MoviesAjaEntity
            ): Boolean {
                return oldItem.moviesId == newItem.moviesId
            }

            override fun areContentsTheSame(
                oldItem: MoviesAjaEntity,
                newItem: MoviesAjaEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(moviesTv: MoviesAjaEntity) {
            with(binding) {
                Glide.with(itemView)
                    .load(base_url_image + moviesTv.poster)
                    .placeholder(R.color.red)
                    .into(poster)

                itemView.setOnClickListener {
                    itemView.context.startActivity(
                        Intent(itemView.context, DetailActivity::class.java)
                            .putExtra("id", moviesTv.moviesId)
                            .putExtra("type", "movies")
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviesAja = getItem(position)
        if (moviesAja != null) {
            holder.bind(moviesAja)
        }
    }

}