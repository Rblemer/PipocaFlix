package br.com.rblemer.domain.usecase

import androidx.paging.PagingData
import br.com.rblemer.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchMoviesUseCase {
    suspend fun execute(query: String) : Flow<PagingData<Movie>>
}