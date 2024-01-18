package br.com.rblemer.data.remote

import br.com.rblemer.data.remote.model.MovieRemoteResponse

interface MovieRemoteData {
    suspend fun getPopularMovies(page: Int = 1) : MovieRemoteResponse
    suspend fun searchMovies(page: Int = 1, query: String) : MovieRemoteResponse
}