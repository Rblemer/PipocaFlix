package br.com.rblemer.pipocaflix.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.rblemer.pipocaflix.ui.components.AnimatedPreloader
import br.com.rblemer.pipocaflix.R
import br.com.rblemer.pipocaflix.ui.screen.Screens
import kotlinx.coroutines.delay

@Composable
fun ScreenSplash(
    navHostController: NavHostController
    ) {
    val colors = MaterialTheme.colorScheme

    LaunchedEffect(key1 = true) {
        delay(3000)
        navHostController.navigate(Screens.Home.route)
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedPreloader(
            modifier = Modifier.size(300.dp),
            anim = R.raw.animation
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.bodyLarge.copy(
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            ),
            color = colors.primary,
            modifier = Modifier
        )
    }
}