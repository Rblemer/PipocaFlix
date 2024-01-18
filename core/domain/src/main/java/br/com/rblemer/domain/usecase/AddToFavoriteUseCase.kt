package br.com.rblemer.domain.usecase

import br.com.rblemer.domain.model.Movie


interface AddToFavoriteUseCase {
    suspend fun execute(movie: Movie)
}