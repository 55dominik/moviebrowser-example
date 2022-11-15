package com.dominik55.moviebrowser.ui.movie.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.dominik55.moviebrowser.R
import com.dominik55.moviebrowser.binding.bindRemoteImage
import com.dominik55.moviebrowser.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMovieDetailsBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)

        viewModel.loadMovie(args.movieId)

        viewModel.movie.observe(this.viewLifecycleOwner) { movie ->
            val imageBaseUrl = requireContext().getString(R.string.image_base_url)
            with(binding) {
                title.text = movie.title
                description.text = movie.overview
                cover.bindRemoteImage(imageBaseUrl + movie.coverUrl)
            }
        }
    }
}
