package br.com.rblemer.domain.model

data class Movie(
    val adult: Boolean,
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
    val isWatched: Boolean = false,
    val isFavorite: Boolean = false
) {
    fun getPosterURL() = "https://image.tmdb.org/t/p/w500${poster_path}"
    fun getReleaseYear() = release_date.substringBefore("-")
}