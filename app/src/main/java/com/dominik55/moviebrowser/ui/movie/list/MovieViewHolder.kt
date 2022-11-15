package com.dominik55.moviebrowser.ui.movie.list

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.dominik55.moviebrowser.R
import com.dominik55.moviebrowser.binding.bindRemoteImage
import com.dominik55.moviebrowser.databinding.ItemMovieBinding
import com.dominik55.moviebrowser.domain.model.Movie

class MovieViewHolder(
    private var binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, context: Context) {
        val imageBaseUrl = context.getString(R.string.image_base_url)
        val progressMultiplier = context.resources.getInteger(R.integer.rating_progress_multiplier)
        with(binding) {
            this.rating.progress = (movie.rating * progressMultiplier).toInt()
            this.title.text = movie.title
            this.genres.text = movie.genres.joinToString(separator = ",")
            this.cover.bindRemoteImage(imageBaseUrl + movie.coverUrl)
        }
    }
}