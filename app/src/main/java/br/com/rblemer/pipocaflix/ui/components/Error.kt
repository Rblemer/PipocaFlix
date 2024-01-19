package br.com.rblemer.pipocaflix.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.rblemer.pipocaflix.R
import br.com.rblemer.domain.error.EmptyResponseError
import br.com.rblemer.domain.error.ShortInputError
import java.net.UnknownHostException

@Composable
fun OnError(
    error: Throwable,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val errorText = when (error) {
        is EmptyResponseError -> stringResource(R.string.error_empty_response)
        is UnknownHostException -> stringResource(R.string.error_no_network)
        is ShortInputError -> stringResource(R.string.error_short_input)
        else -> stringResource(R.string.error_generic)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = errorText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .clickable {
                    onClick?.let { it() }
                }
        )
    }
}