package com.grensil.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val listState = rememberLazyGridState()
    val animeList by viewModel.bookmarkList.collectAsStateWithLifecycle()
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
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "북마크한 애니메이션이 없습니다",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "관심있는 애니메이션을 북마크해보세요",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyVerticalGrid(
                state = listState,
                columns = GridCells.Adaptive(minSize = 160.dp),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(animeList.size, key = { index -> animeList[index].anime_id }) {
                    FavoriteItemView(
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
fun FavoriteItemView(
    animeData: AnimeDto,
    onBookmark: (AnimeDto) -> Unit,
    onDetailClick: (AnimeDto) -> Unit
) {
    Card(
        modifier = Modifier
            .aspectRatio(0.7f)
            .clickable { onDetailClick(animeData) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(animeData.anime_img),
                contentDescription = animeData.anime_name,
                contentScale = ContentScale.Crop
            )

            // 그라데이션 오버레이 효과를 위한 Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.BottomCenter)
            )

            // 북마크 버튼
            FloatingActionButton(
                onClick = { onBookmark(animeData) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(40.dp),
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = if (animeData.bookmarked == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (animeData.bookmarked == true) "북마크 해제" else "북마크 추가",
                    modifier = Modifier.size(24.dp)
                )
            }

            // 애니메이션 제목
            Text(
                text = animeData.anime_name ?: "",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
