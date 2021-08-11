package com.makhalibagas.moviesaja.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makhalibagas.moviesaja.R
import com.makhalibagas.moviesaja.data.source.local.entity.TvShowsAjaEntity
import com.makhalibagas.moviesaja.databinding.ItemsRowBinding
import com.makhalibagas.moviesaja.ui.DetailActivity

class TvAdapter : PagedListAdapter<TvShowsAjaEntity, TvAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        const val base_url_image = "https://image.tmdb.org/t/p/w185"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowsAjaEntity>() {
            override fun areItemsTheSame(
                oldItem: TvShowsAjaEntity,
                newItem: TvShowsAjaEntity
            ): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(
                oldItem: TvShowsAjaEntity,
                newItem: TvShowsAjaEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(moviesTv: TvShowsAjaEntity) {
            with(binding) {
                Glide.with(itemView)
                    .load(base_url_image + moviesTv.poster)
                    .placeholder(R.color.red)
                    .into(poster)

                itemView.setOnClickListener {
                    itemView.context.startActivity(
                        Intent(itemView.context, DetailActivity::class.java)
                            .putExtra("id", moviesTv.tvId)
                            .putExtra("type", "tv")
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