package br.com.rblemer.data.usecase

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import br.com.rblemer.data.repository.MovieRepository
import br.com.rblemer.domain.error.ShortInputError
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.domain.usecase.SearchMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : SearchMoviesUseCase {
    override suspend fun execute(query: String): Flow<PagingData<Movie>> = flow {
        if (query.length < 3){
            val pagingData = PagingData.empty<Movie>(LoadStates(
                refresh = LoadState.Error(ShortInputError()),
                prepend = LoadState.Error(ShortInputError()),
                append = LoadState.Error(ShortInputError())
            ))
            emit(pagingData)
        } else {
            repository.searchMovies(query).collect{
                emit(it)
            }
        }
    }
}