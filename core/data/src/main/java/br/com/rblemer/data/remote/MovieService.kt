package br.com.rblemer.data.remote

import br.com.rblemer.data.remote.model.MovieRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieRemoteResponse

    @GET("search/movie")
    suspend fun searchMovie(@Query("page") page: Int, @Query("query") query: String): MovieRemoteResponse
}