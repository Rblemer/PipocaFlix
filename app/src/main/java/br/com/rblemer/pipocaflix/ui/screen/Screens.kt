package br.com.rblemer.pipocaflix.ui.screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.rblemer.pipocaflix.R

sealed class Screens(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    data object Home : Screens("home", R.string.title_home, Icons.Default.Home)
    data object Search : Screens("search", R.string.title_search, Icons.Default.Search)
    data object Favorites : Screens("favorites", R.string.title_favorite, Icons.Default.Favorite)
}