package br.com.rblemer.pipocaflix.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import br.com.rblemer.pipocaflix.ui.theme.PipocaFlixTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        renderUi()
    }

    private fun renderUi() = setContent {
        var isDarkTheme by remember { mutableStateOf(false) }
        val navController = rememberNavController()
        PipocaFlixTheme(darkTheme = isDarkTheme) {
            CompositionLocalProvider(
                LocalNavController provides navController
            ) {
                MainContent(
                    darkTheme = isDarkTheme,
                    onThemeUpdated = {isDarkTheme = !isDarkTheme}
                )
            }
        }
    }
}