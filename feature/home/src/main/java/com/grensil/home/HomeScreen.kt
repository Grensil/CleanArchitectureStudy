package com.grensil.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.grensil.domain.dto.AnimeDto
import java.net.URLEncoder

@Composable
fun HomeScreen(viewModel: AnimeListViewModel = hiltViewModel(), navController: NavHostController) {

    val listState = rememberLazyListState()
    val animeList by viewModel.animeList.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        } else if (animeList.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "애니메이션 목록을 불러올 수 없습니다",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(animeList.size) {
                    AnimeItemView(
                        animeData = animeList[it],
                        onBookmark = { animeDto ->
                            if (animeDto.bookmarked == true) {
                                viewModel.deleteBookmark(animeDto.anime_id)
                            } else {
                                viewModel.insertBookmark(animeDto.copy(bookmarked = true))
                            }
                        }, onDetailClick = { animeDto ->
                            val encodedImg = URLEncoder.encode(animeDto.anime_img, "UTF-8")
                            val encodedName = URLEncoder.encode(animeDto.anime_name, "UTF-8")

                            navController.navigate("detail/${animeDto.anime_id}/${encodedName}/${encodedImg}/${animeDto.bookmarked}") {
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
            }
        }
    }
}

@Composable
fun AnimeItemView(animeData: AnimeDto, onBookmark: (AnimeDto) -> Unit, onDetailClick : (AnimeDto) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDetailClick(animeData) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter(animeData.anime_img),
                    contentDescription = animeData.anime_name,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = animeData.anime_name ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = { onBookmark(animeData) }
            ) {
                Icon(
                    imageVector = if (animeData.bookmarked == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (animeData.bookmarked == true) "북마크 해제" else "북마크 추가",
                    tint = if (animeData.bookmarked == true) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}