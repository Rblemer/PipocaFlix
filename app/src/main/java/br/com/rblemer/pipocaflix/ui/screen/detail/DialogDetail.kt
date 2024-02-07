package br.com.rblemer.pipocaflix.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import br.com.rblemer.pipocaflix.R
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.pipocaflix.TestTags.ADD_TO_FAVORITES_BUTTON
import br.com.rblemer.pipocaflix.ui.components.OnLoading
import br.com.rblemer.pipocaflix.ui.forwardingPainter
import br.com.rblemer.pipocaflix.ui.theme.dimen16Dp
import br.com.rblemer.pipocaflix.ui.theme.dimen2Dp
import br.com.rblemer.pipocaflix.ui.theme.dimen4Dp
import br.com.rblemer.pipocaflix.ui.theme.dimen8Dp
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

@Composable
fun DialogDetail(
    movie: Movie,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    dialogViewModel: DialogViewModel = hiltViewModel()
) {
    dialogViewModel.isFavorite(movie)
    dialogViewModel.isWatched(movie)
    val uiState = dialogViewModel.screenDialogState.collectAsState().value
    when {
        uiState.isFavorite == null -> {
            OnLoading()
        }

        else -> {
            ScreenContent(
                movie = movie,
                onDismiss = onDismiss,
                isFavorite = uiState.isFavorite,
                isWatched = uiState.isWatched ?: false,
                onFavoriteButtonClicked = { isFavorite ->
                    dialogViewModel.updateIsFavoriteState(movie = movie.copy(isFavorite = isFavorite))
                },
                onWatchedButtonClicked = { isWatched ->
                    dialogViewModel.updateIsWatchedState(movie = movie.copy(isWatched = isWatched))
                },
                modifier = modifier
            )
        }
    }

}

@Composable
private fun ScreenContent(
    movie: Movie,
    onDismiss: () -> Unit,
    isFavorite: Boolean,
    isWatched: Boolean,
    onFavoriteButtonClicked: (Boolean) -> Unit,
    onWatchedButtonClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card(modifier = modifier, shape = MaterialTheme.shapes.large) {
            Column(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                AppBar(
                    modifier = Modifier,
                    onDismiss = onDismiss
                )
                DialogDetailTop(
                    movie = movie,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                )
                DialogDetailTitle(
                    title = movie.title,
                    originalTitle = movie.original_title,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp)
                )
                DialogDetailMovieFields(
                    movie = movie,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp)
                )
                DialogDetailRateStars(
                    voteAverage = movie.vote_average,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp)
                )
                DialogDetailMiddle(
                    isWatched = isWatched,
                    isFavorite = isFavorite,
                    onFavoriteButtonClicked = {
                        onFavoriteButtonClicked(!isFavorite)
                    },
                    onWatchedButtonClicked = {
                        onWatchedButtonClicked(!isWatched)
                    }
                )
                DialogDetailBottom(movie)
            }
        }
    }
}

@Composable
private fun AppBar(modifier: Modifier, onDismiss: () -> Unit) {
    val colors = MaterialTheme.colorScheme
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = { onDismiss() }) {
            Icon(
                Icons.Filled.Close,
                contentDescription = null,
                tint = colors.primary,
                modifier = modifier
            )
        }
    }
}

@Composable
fun DialogDetailTop(movie: Movie, modifier: Modifier) {
    AsyncImage(
        model = movie.getPosterURL(),
        contentDescription = null,
        modifier = modifier
            .size(200.dp),
        error = forwardingPainter(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        ),
        placeholder = forwardingPainter(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        ),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun DialogDetailTitle(title: String, originalTitle: String, modifier: Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                letterSpacing = 3.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            ),
        )
        if (originalTitle.isNotBlank() && title != originalTitle) {
            Text(
                text = "(${originalTitle})",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontStyle = FontStyle.Italic,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}

@Composable
private fun DialogDetailMovieFields(movie: Movie, modifier: Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier) {
        val context = LocalContext.current
        MovieField(context.getString(R.string.label_release_date), movie.release_date)
        MovieField(context.getString(R.string.label_votes), movie.vote_average.toString())
        MovieField(context.getString(R.string.label_note), movie.vote_count.toString())
    }
}

@Composable
private fun MovieField(name: String, value: String) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium.copy(letterSpacing = 1.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp),
        )
    }
}

@Composable
private fun DialogDetailRateStars(voteAverage: Double, modifier: Modifier) {
    val colors = MaterialTheme.colorScheme
    Row(modifier.padding(start = 4.dp)) {
        val maxVote = 10
        val starCount = 5
        repeat(starCount) { starIndex ->
            val voteStarCount = voteAverage / (maxVote / starCount)
            val asset = when {
                voteStarCount >= starIndex + 1 -> Icons.Filled.Star
                voteStarCount in starIndex.toDouble()..(starIndex + 1).toDouble() -> Icons.Filled.StarHalf
                else -> Icons.Filled.StarOutline
            }
            Icon(
                imageVector = asset,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
        }
    }
}

@Composable
fun DialogDetailMiddle(
    onFavoriteButtonClicked: () -> Unit,
    onWatchedButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isWatched: Boolean,
    isFavorite: Boolean
) {
    val colors = MaterialTheme.colorScheme
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = dimen16Dp),
        horizontalArrangement = if (isFavorite) Arrangement.SpaceBetween else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isFavorite) {
            Column(
                modifier = Modifier
                    .clickable {
                        onWatchedButtonClicked()
                    }
            ) {
                Icon(
                    imageVector = if (isWatched) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null,
                    tint = colors.primary
                )
                Spacer(modifier = Modifier.width(dimen2Dp))
                Text(
                    text = if (isWatched) stringResource(R.string.label_mark_as_not_watched) else stringResource(
                        R.string.label_mark_as_watched
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(dimen8Dp))
        }

        Column(
            modifier = Modifier
                .clickable {
                    onFavoriteButtonClicked()
                }
                .testTag(ADD_TO_FAVORITES_BUTTON)
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                contentDescription = null,
                modifier = Modifier
                    .height(IntrinsicSize.Max),
                tint = colors.primary
            )
            Spacer(modifier = Modifier.width(dimen2Dp))
            Text(
                text = if (isFavorite) stringResource(R.string.label_remove_from_favorites) else stringResource(
                    R.string.label_add_to_favorites
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}


@Composable
fun DialogDetailBottom(movie: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.label_overview),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(dimen2Dp))
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(dimen4Dp))
    }
}


@Preview
@Composable
fun ScreenContentPreview() {
    ScreenContent(
        Movie(
            adult = false,
            id = 926393,
            original_title = "The Equalizer 3",
            overview = "Sentindo-se em casa no sul da Itália, o ex-agente Robert McCall logo descobre que seus novos amigos estão sob o controle dos chefes do crime local.  À medida que os acontecimentos se tornam mortais, McCall sabe o que tem de fazer: tornar-se o protetor dos seus amigos, enfrentando a máfia.",
            poster_path = "/AnJOKbSQsp0QqiUhsQooqFRjPsD.jpg",
            release_date = "2023-08-30",
            title = "O Protetor: Capítulo Final",
            vote_count = 23,
            vote_average = 4.7,
        ),
        isFavorite = true,
        isWatched = true,
        onDismiss = { },
        onFavoriteButtonClicked = {},
        onWatchedButtonClicked = {}
    )
}