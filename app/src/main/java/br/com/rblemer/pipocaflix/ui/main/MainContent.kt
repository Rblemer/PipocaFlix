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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.rblemer.pipocaflix.navigation.AppBottomNavBar
import br.com.rblemer.pipocaflix.navigation.AppNavHost
import br.com.rblemer.pipocaflix.ui.components.AppTopBar
import br.com.rblemer.pipocaflix.ui.screen.Screens

val LocalNavController = compositionLocalOf<NavHostController> { error("No nav controller") }
val LocalDarkTheme = compositionLocalOf { mutableStateOf(false) }
@Composable
fun MainContent() {
    val navController = LocalNavController.current

    val colors = MaterialTheme.colorScheme
    val state = rememberSaveable { (mutableStateOf(false)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        Screens.Splash.route -> state.value = false
        else -> state.value = true
    }

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
                    if (state.value) {
                        AppTopBar(
                            topBarState = state
                        )
                    }
                }
            }
        },
        bottomBar = {
            if (state.value) {
                AppBottomNavBar(
                    navController = navController,
                    bottomBarState = state
                )
            }
        }
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
