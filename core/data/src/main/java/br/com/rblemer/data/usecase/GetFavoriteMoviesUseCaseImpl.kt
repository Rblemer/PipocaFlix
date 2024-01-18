package br.com.rblemer.data.usecase

import br.com.rblemer.data.repository.MovieRepository
import br.com.rblemer.domain.error.EmptyResponseError
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.domain.usecase.GetFavoriteMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import br.com.rblemer.domain.Result

class GetFavoriteMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetFavoriteMoviesUseCase {
    override suspend fun execute(): Flow<Result<List<Movie>>> = flow {
        repository.getFavoriteMovies()
            .catch {
                emit(Result.Error(it))
            }
            .collect {
                if (it.isEmpty()) {
                    emit(Result.Error(EmptyResponseError()))
                } else {
                    emit(Result.Success(it))
                }
            }
    }
}