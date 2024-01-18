package br.com.rblemer.data.local

import br.com.rblemer.data.local.database.dao.MovieDao
import br.com.rblemer.data.local.model.MovieDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataImpl @Inject constructor(
    private val moviesDAO: MovieDao
) : MovieLocalData {
    override suspend fun getFavoriteMoviesList() = moviesDAO.getFavoriteMovies()

    override suspend fun markMovieAsWatched(movieId: Int) {
        moviesDAO.markMovieAsWatched(movieId = movieId)
    }

    override suspend fun markMovieAsNotWatched(movieId: Int) {
        moviesDAO.markMovieAsNotWatched(movieId = movieId)
    }

    override suspend fun addToFavorites(movie: MovieDB) {
        moviesDAO.addMovie(movie = movie)
    }

    override suspend fun removeFromFavorites(movie: MovieDB) {
        moviesDAO.deleteMovie(movie = movie)
    }
    override suspend fun isFavorite(movie: MovieDB): Flow<Boolean> = moviesDAO.isFavorite(movieId = movie.id)
    override suspend fun isWatched(movie: MovieDB): Flow<Int> = moviesDAO.isWatched(movieId = movie.id)

}