package br.com.rblemer.data.remote

import br.com.rblemer.data.remote.model.MovieRemoteResponse
import javax.inject.Inject

class MovieRemoteDataImpl @Inject constructor (
    private val service: MovieService
) : MovieRemoteData {
    override suspend fun getPopularMovies(page: Int): MovieRemoteResponse {
        return service.getPopularMovies(page)
    }

    override suspend fun searchMovies(page: Int, query: String): MovieRemoteResponse {
        return service.searchMovie(page, query)
    }
}