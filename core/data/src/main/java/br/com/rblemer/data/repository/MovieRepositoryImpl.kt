package br.com.rblemer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.rblemer.data.local.MovieLocalData
import br.com.rblemer.data.mapper.toMovie
import br.com.rblemer.data.mapper.toMovieDB
import br.com.rblemer.data.remote.PopularMoviesPagingSource
import br.com.rblemer.data.remote.MovieRemoteData
import br.com.rblemer.data.remote.SearchMoviesPagingSource
import br.com.rblemer.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor (
    private val remoteData: MovieRemoteData,
    private val localData: MovieLocalData
) : MovieRepository {
    override suspend fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE),
            pagingSourceFactory = {
                PopularMoviesPagingSource(remoteData)
            }
        ).flow
    }

    override suspend fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE),
            pagingSourceFactory = {
                SearchMoviesPagingSource(remoteData, query)
            }
        ).flow
    }

    override suspend fun getFavoriteMovies(): Flow<List<Movie>> = localData.getFavoriteMoviesList().map { list ->
        list.map {
            it.toMovie()
        }
    }

    override suspend fun markMovieAsWatched(movie: Movie) = localData.markMovieAsWatched(movieId = movie.id)

    override suspend fun markMovieAsNotWatched(movie: Movie) = localData.markMovieAsNotWatched(movieId = movie.id)

    override suspend fun addToFavorites(movie: Movie) {
        localData.addToFavorites(movie = movie.toMovieDB())
    }

    override suspend fun removeFromFavorites(movie: Movie) {
        localData.removeFromFavorites(movie = movie.toMovieDB())
    }

    override suspend fun isFavorite(movie: Movie) : Flow<Boolean> = localData.isFavorite(movie.toMovieDB())
    override suspend fun isWatched(movie: Movie) : Flow<Boolean> = flow {
        localData.isWatched(movie.toMovieDB())
            .catch {
                emit(false)
            }
            .collect{ result ->
                if (result == 1){
                    emit(true)
                } else {
                    emit(false)
                }
            }
    }

    companion object {
        const val PAGE_SIZE = 10
        const val PREFETCH_DISTANCE = 2
    }
}