package br.com.rblemer.data.usecase

import br.com.rblemer.data.repository.MovieRepository
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.domain.usecase.IsWatchedUseCase
import javax.inject.Inject

class IsWatchedUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : IsWatchedUseCase {
    override suspend fun execute(movie: Movie) = repository.isWatched(movie = movie)
}