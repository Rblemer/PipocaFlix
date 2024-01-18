package br.com.rblemer.data.mapper

import br.com.rblemer.domain.model.Movie
import br.com.rblemer.data.local.model.MovieDB
import br.com.rblemer.data.remote.model.MovieRemote

fun MovieRemote.toMovie() = Movie(
    adult = adult,
    id = id,
    original_title = original_title,
    overview = overview,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    vote_average = vote_average,
    vote_count = vote_count
)

fun Movie.toMovieDB() = MovieDB(
    adult = adult,
    id = id,
    original_title = original_title,
    overview = overview,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    vote_average = vote_average,
    vote_count = vote_count,
    alreadyWatched = isWatched
)

fun MovieDB.toMovie() = Movie(
    adult = adult,
    id = id,
    original_title = original_title,
    overview = overview,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    vote_average = vote_average,
    vote_count = vote_count,
    isWatched = alreadyWatched
)