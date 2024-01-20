package br.com.rblemer.pipocaflix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.rblemer.pipocaflix.R
import br.com.rblemer.pipocaflix.ui.theme.dimen8Dp
import br.com.rblemer.pipocaflix.ui.theme.dimen50Dp

@Composable
fun OnLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(dimen50Dp)
            )
            Spacer(modifier = Modifier.height(dimen8Dp))
            Text(
                text = stringResource(R.string.label_loading),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}