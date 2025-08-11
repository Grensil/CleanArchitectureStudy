package com.grensil.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.grensil.domain.dto.AnimeDto

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel(), navController: NavHostController, animeDto : AnimeDto) {

    val animeDetail by viewModel.animeDetail.collectAsStateWithLifecycle()

    // 초기 데이터 설정
    LaunchedEffect(animeDto) {
        viewModel.setAnimeDetail(animeDto)
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {

        Image(
            modifier = Modifier.size(32.dp).clickable {
                navController.popBackStack()
            },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }) {  innerPadding ->

        Column(modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding()).padding(horizontal = 16.dp), verticalArrangement = Arrangement.Top) {
            
            Image(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).aspectRatio(1f),
                painter = rememberAsyncImagePainter(animeDetail?.anime_img),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(modifier = Modifier.weight(1f),
                    text = animeDetail?.anime_name?:"",
                    color = Color.Black,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1)

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    modifier = Modifier.size(32.dp).clickable {
                        animeDetail?.let { anime ->
                            if (anime.bookmarked == true) {
                                viewModel.deleteBookmark(anime.anime_id)
                                viewModel.setAnimeDetail(anime.copy(bookmarked = false))
                            } else {
                                viewModel.insertBookmark(anime.copy(bookmarked = true))
                                viewModel.setAnimeDetail(anime.copy(bookmarked = true))
                            }
                        }
                    },
                    imageVector = if (animeDetail?.bookmarked == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}