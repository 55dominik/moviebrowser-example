package com.dominik55.moviebrowser.ui.movie.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dominik55.moviebrowser.databinding.ItemMovieBinding
import com.dominik55.moviebrowser.domain.model.Movie

class MovieAdapter(private val onItemClicked: (Movie) -> Unit) :
    ListAdapter<Movie, MovieViewHolder>(MovieComparator) {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        context = parent.context
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current, context)
    }
}
