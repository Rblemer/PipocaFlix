package br.com.rblemer.pipocaflix.ui.main

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import br.com.rblemer.pipocaflix.ui.theme.PipocaFlixTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        renderUi()
    }

    @SuppressLint("CommitPrefEdits")
    private fun renderUi() = setContent {
        val systemTheme = isSystemInDarkTheme()
        val isDarkTheme = remember { mutableStateOf(systemTheme) }
        val navController = rememberNavController()

        val nightMode = sharedPrefs.getBoolean("night", false)
        val editor = sharedPrefs.edit()

        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            editor.putBoolean("night", true)
            editor.apply()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            editor.putBoolean("night", false)
            editor.apply()
        }

        PipocaFlixTheme(isDarkTheme = isDarkTheme.value) {
            CompositionLocalProvider(
                LocalNavController provides navController,
                LocalDarkTheme provides isDarkTheme
            ) {
                MainContent()
            }
        }
    }
}