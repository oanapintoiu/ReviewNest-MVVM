package com.example.reviewnest_mvvm.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.reviewnest_mvvm.viewmodel.MovieListViewModel
import com.example.reviewnest_mvvm.ui.theme.Purple80
import com.example.reviewnest_mvvm.ui.theme.White

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onMovieClicked: (Int) -> Unit
) {
    val movies by viewModel.movies.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 20.dp, vertical = 50.dp )
    ) {
        Text(
            text = "ReviewNest",
            color =  Purple80,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp,
            modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth()
        )

        Text(
            text = "Top 20 Best Rated Movies",
            color = White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies) { movie ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMovieClicked(movie.id.toInt()) }
                ) {
                    Text(
                        text = "${movie.title} (${movie.year})",
                        color = White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.5.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Image(
                        painter = rememberAsyncImagePainter(movie.landscapeMoviePoster),
                        contentDescription = movie.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.78f),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}
