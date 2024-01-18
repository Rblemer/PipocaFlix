package br.com.rblemer.data.remote.model

data class MovieRemoteResponse(
    val page: Int,
    val results: List<MovieRemote>,
    val total_pages: Int,
    val total_results: Int
)