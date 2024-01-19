package br.com.rblemer.pipocaflix.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.rblemer.pipocaflix.TestTags.BOTTOM_NAV_FAVORITE
import br.com.rblemer.pipocaflix.TestTags.BOTTOM_NAV_HOME
import br.com.rblemer.pipocaflix.TestTags.BOTTOM_NAV_SEARCH
import br.com.rblemer.pipocaflix.ui.screen.Screens

@Composable
fun MoviesBottomNavigation(
    darkTheme: Boolean = false,
    navController: NavController,
    items: List<Screens>,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val tint by animateColorAsState(
        targetValue =  if (darkTheme) colors.onSurface else colors.primary,
        label = "",
        animationSpec = tween(1000)
    )

    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        screen.icon, null,
                        tint = tint
                    )
                },
                label = { Text(stringResource(screen.title)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.testTag(
                    when (screen) {
                        Screens.Home -> BOTTOM_NAV_HOME
                        Screens.Search -> BOTTOM_NAV_SEARCH
                        else -> BOTTOM_NAV_FAVORITE
                    }
                )
            )
        }
    }
}