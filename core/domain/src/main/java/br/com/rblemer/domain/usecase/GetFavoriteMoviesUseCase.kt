package br.com.rblemer.domain.usecase

import br.com.rblemer.domain.model.Movie
import br.com.rblemer.domain.Result
import kotlinx.coroutines.flow.Flow

interface GetFavoriteMoviesUseCase {
    suspend fun execute() : Flow<Result<List<Movie>>>
}