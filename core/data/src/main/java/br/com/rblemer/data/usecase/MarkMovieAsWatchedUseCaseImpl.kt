package br.com.rblemer.data.usecase

import br.com.rblemer.data.repository.MovieRepository
import br.com.rblemer.domain.error.InvalidMovieIdError
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.domain.usecase.MarkMovieAsWatchedUseCase
import javax.inject.Inject

class MarkMovieAsWatchedUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : MarkMovieAsWatchedUseCase {
    override suspend fun execute(movie: Movie) {
        if (movie.id >= 0) {
            repository.markMovieAsWatched(movie = movie)
        } else {
            throw InvalidMovieIdError()
        }
    }
}