package br.com.rblemer.pipocaflix.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

val md_theme_light_primary = Color(0xFFE50914)
val md_theme_light_primaryVariant = Color(0xFF971C1C)
val md_theme_light_secondary = Color(0xFFE50914)
val md_theme_light_secondaryVariant = Color(0xFF831010)
val md_theme_light_background = Color(0xFFFFFBFF)
val md_theme_light_surface = Color(0xFFFFFBFF)
val md_theme_light_error = Color(0xFFE50914)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_onBackground = Color(0xFF201A19)
val md_theme_light_onSurface = Color(0xFF1C1C1C)
val md_theme_light_onError = Color(0xFFFFFFFF)

val md_theme_dark_primary = Color(0xFFE50914)
val md_theme_dark_primaryVariant = Color(0xFF971C1C)
val md_theme_dark_secondary = Color(0xFFE50914)
val md_theme_dark_secondaryVariant = Color(0xFF831010)
val md_theme_dark_background = Color(0xFF1C1C1C)
val md_theme_dark_surface = Color(0xFF1C1C1C)
val md_theme_dark_error = Color(0xFFCF6679)
val md_theme_dark_onPrimary = Color(0xFF670500)
val md_theme_dark_onSecondary = Color(0xFF442A25)
val md_theme_dark_onBackground = Color(0xFFEDE0DD)
val md_theme_dark_onSurface = Color(0xFFEDE0DD)
val md_theme_dark_onError = Color(0xFF1C1C1C)

@Composable
fun Color.Companion.rateColors(movieRate: Double): List<Color> = remember(movieRate) {
    when {
        movieRate <= 4.5 -> listOf(Color(0xffe32d20), Color(0xff9c180e))
        movieRate < 7 -> listOf(Color(0xffe36922), Color(0xff963d09))
        movieRate < 8.5 -> listOf(Color(0xff87bf32), Color(0xff578216))
        else -> listOf(Color(0xff34c937), Color(0xff0d750f))
    }
}
