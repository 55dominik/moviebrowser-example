package com.dominik55.moviebrowser.ui.movie.list

import androidx.recyclerview.widget.DiffUtil
import com.dominik55.moviebrowser.domain.model.Movie

object MovieComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}