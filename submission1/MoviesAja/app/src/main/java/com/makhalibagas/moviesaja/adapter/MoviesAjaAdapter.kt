package com.makhalibagas.moviesaja.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makhalibagas.moviesaja.data.MoviesAja
import com.makhalibagas.moviesaja.databinding.ItemsRowBinding
import com.makhalibagas.moviesaja.ui.DetailActivity

class MoviesAjaAdapter(val moviesAjaList: List<MoviesAja>) : RecyclerView.Adapter<MoviesAjaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding : ItemsRowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(movies : MoviesAja){
            with(binding){
                Glide.with(itemView)
                        .load(movies.poster)
                        .into(poster)

                itemView.setOnClickListener {
                    itemView.context.startActivity(Intent(itemView.context, DetailActivity::class.java)
                            .putExtra("moviesAja", movies.id.toString())
                            .putExtra("type", "typeMovies")
                    )

                    
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviesAja = moviesAjaList[position]
        holder.bind(moviesAja)
    }

    override fun getItemCount(): Int = moviesAjaList.size

}