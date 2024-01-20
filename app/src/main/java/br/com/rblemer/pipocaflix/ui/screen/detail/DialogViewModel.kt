package br.com.rblemer.pipocaflix.ui.screen.detail

import br.com.rblemer.domain.model.Movie

interface DialogViewModel {
    fun isFavorite(movie: Movie)
    fun isWatched(movie: Movie)
    fun updateIsWatchedState(movie: Movie)
    fun updateIsFavoriteState(movie: Movie)
}