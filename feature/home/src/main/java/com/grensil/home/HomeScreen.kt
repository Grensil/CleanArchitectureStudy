package com.grensil.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.grensil.domain.dto.AnimeDto
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(viewModel: AnimeListViewModel = hiltViewModel()) {

    val insets = WindowInsets.statusBars.asPaddingValues()
    val statusBarHeight = with(LocalDensity.current) { insets.calculateTopPadding() }

    val animeList by viewModel.animeList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.deleteAllBookmark()
        viewModel.getAnimeList()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            items(animeList.size) {
                AnimeItemView(animeData = animeList[it],
                    onBookmark = { animeDto ->
                        if(animeDto.bookmarked == true) {
                            viewModel.deleteBookmark(animeDto.anime_id)
                        }
                        else {
                            viewModel.insertBookmark(animeDto)
                        }
                    })
            }
        }
    }
}

@Composable
fun AnimeItemView(animeData: AnimeDto, onBookmark : (AnimeDto) -> Unit) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .clickable {
            onBookmark(animeData)
        }, verticalAlignment = Alignment.CenterVertically) {
        Image(modifier = Modifier.size(40.dp),
            painter = rememberAsyncImagePainter(animeData.anime_img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp).fillMaxHeight())

        Text(modifier = Modifier.weight(1f),
            text = animeData.anime_name?:"",
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1)

        Spacer(modifier = Modifier.width(16.dp).fillMaxHeight())

        Image(
            modifier = Modifier.size(32.dp),
            imageVector = if(animeData.bookmarked == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp).fillMaxHeight())
    }
}


@Preview
@Composable
fun PreviewAnimeListScreen() {


}