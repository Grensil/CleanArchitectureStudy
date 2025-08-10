package com.grensil.data.repository

import com.grensil.data.datasource.AnimeLocalDataSource
import com.grensil.data.datasource.AnimeRemoteDataSource
import com.grensil.data.mapper.toAnimeDto
import com.grensil.data.mapper.toFavoriteAnimeEntity
import com.grensil.domain.dto.AnimeDto
import com.grensil.domain.dto.FavoriteAnimeDto
import com.grensil.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepositoryImpl @Inject constructor(
    private val remoteDataSource: AnimeRemoteDataSource,
    private val localDataSource: AnimeLocalDataSource
) : AnimeRepository {

    override suspend fun getAnimeList(): Flow<List<AnimeDto>> {
        return combine(
            remoteDataSource.getAnimeList(),
            localDataSource.getBookmarkList()
        ) { remoteList, localList ->
            val transRemoteList = remoteList.map { it.toAnimeDto() }
            val transLocalList = localList.map { it.toAnimeDto() }

            transRemoteList.map { anime ->
                anime.copy(bookmarked = transLocalList.any { it.anime_id == anime.anime_id })
            }
        }
    }

    override suspend fun getBookmarkList(): Flow<List<AnimeDto>> {
        return localDataSource.getBookmarkList().map { list ->
            list.map { it.toAnimeDto() }
        }
    }

    override suspend fun removeAnimeList() {
        localDataSource.getBookmarkList()
    }

    override suspend fun addBookmark(animeDto: AnimeDto) {
        localDataSource.addBookmark(animeDto.toFavoriteAnimeEntity())
    }

    override suspend fun removeBookmark(animeId: Int) {
        localDataSource.removeBookmark(animeId)
    }


}
