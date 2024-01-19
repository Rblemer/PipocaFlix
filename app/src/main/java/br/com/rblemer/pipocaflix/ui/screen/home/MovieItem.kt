package br.com.rblemer.pipocaflix.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import br.com.rblemer.pipocaflix.R
import br.com.rblemer.domain.model.Movie
import br.com.rblemer.pipocaflix.TestTags.MOVIE_ITEM
import br.com.rblemer.pipocaflix.ui.forwardingPainter
import br.com.rblemer.pipocaflix.ui.theme.dimen8Dp
import br.com.rblemer.pipocaflix.ui.theme.rateColors
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import java.math.BigDecimal
import java.math.RoundingMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    isDisabled: Boolean = false,
    onCardClicked: ((Movie) -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .alpha(if (isDisabled) 0.4F else 1.0F)
            .testTag(MOVIE_ITEM),
        shape = RoundedCornerShape(size = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimen8Dp
        ),
        onClick = {
            onCardClicked?.let { it(movie) }
        },
    ) {
        Box {
            MoviePoster(movie.getPosterURL(), movie.title)
            MovieInfo(
                movie,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color(0x97000000)),
            )
            MovieRate(
                movie.vote_average,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .zIndex(2f)
                    .offset(y = 4.dp),
            )
        }
    }
}

@Composable
private fun BoxScope.MoviePoster(posterPath: String, movieName: String) {
    val painter = rememberAsyncImagePainter(
        model = posterPath,
        error = rememberVectorPainter(Icons.Filled.BrokenImage),
        placeholder = rememberVectorPainter(Icons.Default.Movie),
    )

    val scale = if (painter.state !is AsyncImagePainter.State.Success) ContentScale.Fit else ContentScale.FillBounds

    AsyncImage(
        model = posterPath,
        contentDescription = null,
        contentScale = scale,
        modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center),
        error = forwardingPainter(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
        )
    )
}

@Composable
private fun MovieRate(rate: Double, modifier: Modifier) {
    val colors = Color.rateColors(movieRate = rate)
    val brush = remember(rate) { Brush.horizontalGradient(colors) }
    Text(
        text = BigDecimal(rate).setScale(1, RoundingMode.HALF_EVEN).toString(),
        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
        modifier = modifier
            .background(brush, RoundedCornerShape(50))
            .padding(horizontal = 10.dp)
            .shadow(8.dp)
    )
}

@Composable
private fun MovieInfo(movie: Movie, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.padding(horizontal = 6.dp, vertical = 4.dp),
    ) {
        MovieName(name = movie.title)
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            MovieFeature(Icons.Default.DateRange, movie.release_date)
            MovieFeature(Icons.Default.ThumbUp, movie.vote_count.toString())
        }
    }
}

@Composable
private fun MovieName(name: String) = Text(
    text = name,
    style = typography.bodySmall.copy(
        color = Color.White,
        letterSpacing = 1.5.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W500,
    ),
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
)

@Composable
private fun MovieFeature(icon: ImageVector, field: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(13.dp)
        )
        Text(
            text = field,
            style = typography.bodyMedium.copy(
                color = Color.White,
                letterSpacing = 1.5.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 2.dp),
        )
    }
}

//@Preview
//@Composable
//fun MovieItemPreview() {
//    MovieItem(
//        Movie(
//            adult = false,
//            id = 926393,
//            original_title = "The Equalizer 3",
//            overview = "Sentindo-se em casa no sul da Itália, o ex-agente Robert McCall logo descobre que seus novos amigos estão sob o controle dos chefes do crime local.  À medida que os acontecimentos se tornam mortais, McCall sabe o que tem de fazer: tornar-se o protetor dos seus amigos, enfrentando a máfia.",
//            poster_path = "/AnJOKbSQsp0QqiUhsQooqFRjPsD.jpg",
//            release_date = "2023-08-30",
//            title = "O Protetor: Capítulo Final",
//            vote_count = 23,
//            vote_average = 4.7,
//        )
//    )
//}