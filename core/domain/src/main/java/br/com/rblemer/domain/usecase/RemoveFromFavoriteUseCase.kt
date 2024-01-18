package br.com.rblemer.domain.usecase

import br.com.rblemer.domain.model.Movie

interface RemoveFromFavoriteUseCase {
    suspend fun execute(movie: Movie)
}