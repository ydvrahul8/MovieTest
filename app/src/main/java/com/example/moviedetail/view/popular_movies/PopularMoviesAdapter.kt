package com.example.moviedetail.view.popular_movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviedetail.R
import com.example.moviedetail.base.OnClickHandler
import com.example.moviedetail.databinding.ItemPopularMoviesBinding
import com.example.moviedetail.model.Movie
import javax.inject.Inject

class PopularMoviesAdapter @Inject constructor(val onClickHandler: OnClickHandler):
    PagingDataAdapter<Movie, PopularMoviesAdapter.PhotoViewHolder>(Movie.CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemPopularMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemPopularMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.clickHandler = onClickHandler
        }
    }
}