package br.com.rblemer.pipocaflix.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.rblemer.pipocaflix.R

sealed class Screens(
    val title: Int? = 0,
    val icon: ImageVector? = null,
    val route: String,
) {
    object Splash : Screens(
        route = "splash",
    )
    object Home : Screens(
        title =  R.string.title_home,
        icon = Icons.Default.Home,
        route = "home",
    )

    object Search : Screens(
        title = R.string.title_search,
        icon = Icons.Default.Search,
        route = "search",
    )

    object Favorites : Screens(
        title = R.string.title_favorite,
        icon = Icons.Default.Favorite,
        route = "favorites",
    )
}