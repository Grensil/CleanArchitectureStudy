package com.grensil.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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


@Composable
fun AnimeListScreen(viewModel: AnimeListViewModel = hiltViewModel()) {

    val insets = WindowInsets.statusBars.asPaddingValues()
    val statusBarHeight = with(LocalDensity.current) { insets.calculateTopPadding() }

    val animeList by viewModel.animeList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAnimeList()
    }

    Column(modifier = Modifier.fillMaxSize().padding(top = statusBarHeight)) {

        animeList.forEachIndexed { index, animeEntity ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {

                Row {
                    Image(modifier = Modifier.size(40.dp),
                        painter = rememberAsyncImagePainter(animeEntity.anime_img),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp).fillMaxHeight())

                    Text(text = animeEntity.anime_name?:"",
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1)

                    Spacer(modifier = Modifier.width(16.dp).fillMaxHeight())
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnimeListScreen() {


}