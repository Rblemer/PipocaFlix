package br.com.rblemer.data.repository

import androidx.paging.PagingData
import br.com.rblemer.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies() : Flow<PagingData<Movie>>
    suspend fun searchMovies(query: String) : Flow<PagingData<Movie>>
    suspend fun getFavoriteMovies() : Flow<List<Movie>>
    suspend fun markMovieAsWatched(movie: Movie)
    suspend fun markMovieAsNotWatched(movie: Movie)
    suspend fun addToFavorites(movie: Movie)
    suspend fun removeFromFavorites(movie: Movie)
    suspend fun isFavorite(movie: Movie) : Flow<Boolean>
    suspend fun isWatched(movie: Movie) : Flow<Boolean>
}