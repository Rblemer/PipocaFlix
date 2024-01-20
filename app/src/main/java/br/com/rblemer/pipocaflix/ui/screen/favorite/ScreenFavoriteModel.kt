package br.com.rblemer.pipocaflix.ui.screen.favorite

import br.com.rblemer.domain.model.Movie

data class ScreenFavoriteModel(
    var isLoading: Boolean,
    var error: Throwable? = null,
    val data: List<Movie> = emptyList()
)
