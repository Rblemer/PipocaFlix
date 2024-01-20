package br.com.rblemer.pipocaflix.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.rblemer.pipocaflix.ui.components.AppTopBar
import br.com.rblemer.pipocaflix.ui.components.MoviesBottomNavigation
import br.com.rblemer.pipocaflix.ui.screen.Screens
import br.com.rblemer.pipocaflix.ui.screen.favorite.ScreenFavorite
import br.com.rblemer.pipocaflix.ui.screen.home.ScreenHome
import br.com.rblemer.pipocaflix.ui.screen.search.ScreenSearch

val LocalNavController = compositionLocalOf<NavHostController> { error("No nav controller") }
@Composable
fun MainContent(darkTheme: Boolean, onThemeUpdated: () -> Unit) {
    val navController = LocalNavController.current

    val screensInBottomNav = listOf(
        Screens.Home,
        Screens.Search,
        Screens.Favorites
    )
    val colors = MaterialTheme.colorScheme

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 16.dp,
            ) {
                Column(
                    Modifier
                        .background(colors.surface)
                        .padding(bottom = 2.dp)
                ) {
                    AppTopBar(darkTheme = darkTheme, onClick = onThemeUpdated)
                }
            }
        },
        bottomBar = {
            MoviesBottomNavigation(
                darkTheme = darkTheme,
                navController,
                screensInBottomNav,
            ) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.route) { ScreenHome(darkTheme = darkTheme) }
            composable(Screens.Favorites.route) { ScreenFavorite(darkTheme = darkTheme) }
            composable(Screens.Search.route) { ScreenSearch(darkTheme = darkTheme) }
        }
    }
}
