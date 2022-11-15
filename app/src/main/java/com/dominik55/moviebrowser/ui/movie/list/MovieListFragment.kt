package com.dominik55.moviebrowser.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dominik55.moviebrowser.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var adapter: MovieAdapter

    private val viewModel: MovieListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMovieListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieListBinding.bind(view)

        adapter = MovieAdapter {
            findNavController().navigate(MovieListFragmentDirections.toMovieDetails(it.id))
        }
        binding.recyclerView.adapter = adapter

        viewModel.loadMovies()

        viewModel.movies.observe(this.viewLifecycleOwner) {
            adapter.submitList(it.toMutableList())
        }
    }
}
