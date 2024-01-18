package br.com.rblemer.domain.usecase

import br.com.rblemer.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IsFavoriteUseCase {
    suspend fun execute(movie: Movie): Flow<Boolean>
}