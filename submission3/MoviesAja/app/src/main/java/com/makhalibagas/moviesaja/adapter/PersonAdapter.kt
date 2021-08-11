package com.makhalibagas.moviesaja.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makhalibagas.moviesaja.R
import com.makhalibagas.moviesaja.data.source.remote.response.Person
import com.makhalibagas.moviesaja.databinding.ItemsPersonBinding

class PersonAdapter(private val personList: List<Person>) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    companion object {
        const val base_url_image = "https://image.tmdb.org/t/p/w185"
    }

    inner class ViewHolder(val binding: ItemsPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            Glide.with(itemView)
                .load(base_url_image + person.profilePath)
                .placeholder(R.color.red)
                .into(binding.person)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personAJa = personList[position]
        holder.bind(personAJa)
    }

    override fun getItemCount(): Int = personList.size

}