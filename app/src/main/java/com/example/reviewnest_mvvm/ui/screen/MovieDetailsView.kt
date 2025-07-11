package com.example.reviewnest_mvvm.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.reviewnest_mvvm.ui.component.ExpandableReviewText
import com.example.reviewnest_mvvm.ui.theme.Purple40
import com.example.reviewnest_mvvm.ui.theme.Purple80
import com.example.reviewnest_mvvm.ui.theme.White
import com.example.reviewnest_mvvm.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    movieId: Int,
    onBack: () -> Unit,
) {

    val movie by viewModel.details.collectAsState()

    // Load the movie details when movieId changes
    LaunchedEffect(movieId) {
        viewModel.loadMovieDetails(movieId)
    }

    movie?.let { details ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = 20.dp, vertical = 50.dp)
        ) {
            Button(
                onClick = onBack,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Back",
                    color = White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = details.title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth(),
                color = White
            )

            Image(
                painter = rememberAsyncImagePainter(details.landscapeMoviePoster),
                contentDescription = "Backdrop for ${details.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.78f),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Rating: ${"%.1f".format(details.rating)}/10",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 10.dp, top = 40.dp),
                color = White,
            )

            Row(modifier = Modifier.padding(bottom = 12.dp),) {
                Text(text = details.year, fontSize = 14.sp, color = White)
                Text(text = " • ", fontSize = 16.sp, color = White)
                Text(text = details.genres.take(3).joinToString(), fontSize = 14.sp, color = White)
                Text(text = " • ", fontSize = 16.sp, color = White)
                Text(text = details.duration, fontSize = 14.sp, color = White)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = true)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Cast",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = White,
                    modifier = Modifier.padding(bottom = 10.dp, top = 30.dp)
                )
                LazyRow {
                    items(details.cast.take(15)) { castMember ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .width(80.dp)
                                .padding(end = 12.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(castMember.profileUrl),
                                contentDescription = castMember.name,
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Purple40),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = castMember.name,
                                fontSize = 14.sp,
                                color = White,
                                maxLines = 2,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .fillMaxWidth(),
                            )
                        }
                    }
                }
                Text(
                    text = "Overview",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = White,
                    modifier = Modifier.padding(bottom = 10.dp, top = 30.dp)
                )
                Text(
                    text = details.overview,
                    fontSize = 14.sp,
                    color = White
                )
                Text(
                    text = "Reviews",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = White,
                    modifier = Modifier.padding(top = 30.dp, bottom = 10.dp)
                )
                if (details.reviews.isEmpty()) {
                    Text(
                        text = "No reviews yet.",
                        fontSize = 14.sp,
                        color = White,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                } else {
                    details.reviews.forEach { review ->
                        Column(modifier = Modifier.padding(bottom = 20.dp)) {
                            Text(
                                text = review.author,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Purple80,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                            ExpandableReviewText(
                                text = review.content,
                                minimizedMaxLines = 3
                            )
                        }
                    }
                }
            }
        }
    }
}
