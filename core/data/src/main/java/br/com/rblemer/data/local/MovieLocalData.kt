package br.com.rblemer.data.local

import br.com.rblemer.data.local.model.MovieDB
import kotlinx.coroutines.flow.Flow

interface MovieLocalData {

    suspend fun getFavoriteMoviesList() : Flow<List<MovieDB>>
    suspend fun markMovieAsWatched(movieId: Int)
    suspend fun markMovieAsNotWatched(movieId: Int)
    suspend fun addToFavorites(movie: MovieDB)
    suspend fun removeFromFavorites(movie: MovieDB)
    suspend fun isFavorite(movie: MovieDB) : Flow<Boolean>
    suspend fun isWatched(movie: MovieDB) : Flow<Int>
}