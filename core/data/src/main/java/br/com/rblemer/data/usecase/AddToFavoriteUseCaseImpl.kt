package br.com.rblemer.data.usecase

import br.com.rblemer.data.repository.MovieRepository
import br.com.rblemer.domain.error.InvalidMovieIdError
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.domain.usecase.AddToFavoriteUseCase
import javax.inject.Inject

class AddToFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : AddToFavoriteUseCase {
    override suspend fun execute(movie: Movie) {
        if (movie.id >= 0 ){
            repository.addToFavorites(movie = movie)
        } else {
            throw InvalidMovieIdError()
        }
    }
}