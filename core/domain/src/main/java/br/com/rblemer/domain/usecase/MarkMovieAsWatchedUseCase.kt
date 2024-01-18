package br.com.rblemer.domain.usecase

import br.com.rblemer.domain.model.Movie

interface MarkMovieAsWatchedUseCase {
    suspend fun execute(movie: Movie)
}