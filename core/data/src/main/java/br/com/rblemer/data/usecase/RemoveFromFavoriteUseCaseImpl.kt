package br.com.rblemer.data.usecase

import br.com.rblemer.data.repository.MovieRepository
import br.com.rblemer.domain.error.InvalidMovieIdError
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.domain.usecase.RemoveFromFavoriteUseCase
import javax.inject.Inject

class RemoveFromFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : RemoveFromFavoriteUseCase {
    override suspend fun execute(movie: Movie) {
        if (movie.id >= 0){
            repository.removeFromFavorites(movie = movie)
        } else {
            throw InvalidMovieIdError()
        }
    }
}