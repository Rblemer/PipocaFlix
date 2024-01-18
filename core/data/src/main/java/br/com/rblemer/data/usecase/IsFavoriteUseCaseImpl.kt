package br.com.rblemer.data.usecase

import br.com.rblemer.data.repository.MovieRepository
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.domain.usecase.IsFavoriteUseCase
import javax.inject.Inject

class IsFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : IsFavoriteUseCase {
    override suspend fun execute(movie: Movie) = repository.isFavorite(movie = movie)
}