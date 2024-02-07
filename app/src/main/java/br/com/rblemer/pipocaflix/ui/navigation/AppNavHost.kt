package br.com.rblemer.pipocaflix.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.rblemer.pipocaflix.ui.screen.Screens
import br.com.rblemer.pipocaflix.ui.screen.favorite.ScreenFavorite
import br.com.rblemer.pipocaflix.ui.screen.home.ScreenHome
import br.com.rblemer.pipocaflix.ui.screen.search.ScreenSearch
import br.com.rblemer.pipocaflix.ui.screen.splash.ScreenSplash

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Screens.Splash.route, modifier = modifier) {
        composable(Screens.Splash.route) {
            ScreenSplash(
                navHostController = navController
            )
        }
        composable(Screens.Home.route) {
            ScreenHome()
        }
        composable(Screens.Search.route) {
            ScreenSearch()
        }
        composable(Screens.Favorites.route) {
            ScreenFavorite()
        }
    }
}
