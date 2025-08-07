package com.grensil.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter


@Composable
fun AnimeRoute() {
    AnimeListScreen()
}

@Composable
fun AnimeListScreen(viewModel: AnimeListViewModel = hiltViewModel()) {

    val animeList by viewModel.animeList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAnimeList()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        animeList.forEachIndexed { index, animeEntity ->
            Column(modifier = Modifier.fillMaxWidth().height(40.dp)) {
                Image(painter = rememberAsyncImagePainter(animeEntity.anime_img),
                    contentDescription = null)
            }
        }
    }
}