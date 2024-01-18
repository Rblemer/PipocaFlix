package br.com.rblemer.data.usecase

import br.com.rblemer.data.repository.MovieRepository
import br.com.rblemer.domain.error.InvalidMovieIdError
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.domain.usecase.MarkMovieAsNotWatchedUseCase
import javax.inject.Inject

class MarkMovieAsNotWatchedUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : MarkMovieAsNotWatchedUseCase {
    override suspend fun execute(movie: Movie) {
        if (movie.id >= 0){
            repository.markMovieAsNotWatched(movie = movie)
        } else {
            throw InvalidMovieIdError()
        }
    }
}