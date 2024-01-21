package br.com.rblemer.pipocaflix.ui.components

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.rblemer.pipocaflix.R
import br.com.rblemer.pipocaflix.ui.main.LocalDarkTheme

@Composable
fun AppTopBar(
    topBarState: MutableState<Boolean>
) {
    val colors = MaterialTheme.colorScheme
    val isDarkTheme = LocalDarkTheme.current
    AnimatedVisibility(
        visible = topBarState.value,
        content = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            letterSpacing = 2.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        ),
                        color = colors.primary,
                        modifier = Modifier
                            .padding(start = 8.dp),
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_popcorn),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(start = 2.dp),
                        tint = colors.primary
                    )
                }


                val icon = if (isDarkTheme.value) Icons.Default.NightsStay else Icons.Default.WbSunny
                IconButton(
                    onClick = {
                        isDarkTheme.value = !isDarkTheme.value
                    }
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = colors.primary
                    )
                }
            }
        }
    )
}