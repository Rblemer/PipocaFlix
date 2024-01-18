package br.com.rblemer.data.usecase

import androidx.paging.PagingData
import br.com.rblemer.data.repository.MovieRepository
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetPopularMoviesUseCase {
    override suspend fun execute(): Flow<PagingData<Movie>> = repository.getPopularMovies()
}