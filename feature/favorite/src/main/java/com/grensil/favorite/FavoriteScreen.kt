package com.grensil.favorite

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
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

    LaunchedEffect(Unit) {
        viewModel.getBookmarkList()
    }


    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Adaptive(minSize = 180.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
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

@Composable
fun FavoriteItemView(
    animeData: AnimeDto,
    onBookmark: (AnimeDto) -> Unit,
    onDetailClick: (AnimeDto) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable {
                onDetailClick(animeData)
            }, contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            painter = rememberAsyncImagePainter(animeData.anime_img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Image(
            modifier = Modifier
                .size(32.dp).zIndex(1f).align(Alignment.BottomEnd)
                .padding(bottom = 6.dp, end = 6.dp)
                .clickable {
                    onBookmark(animeData)
                },
            imageVector = if (animeData.bookmarked == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
