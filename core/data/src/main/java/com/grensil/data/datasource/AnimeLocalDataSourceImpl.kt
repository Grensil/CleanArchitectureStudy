package com.grensil.data.datasource

import com.grensil.data.local.dao.FavoriteAnimeDao
import com.grensil.data.local.entity.FavoriteAnimeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AnimeLocalDataSourceImpl @Inject constructor(
    private val dao: FavoriteAnimeDao
) : AnimeLocalDataSource {

    override fun getBookmarkList(): Flow<List<FavoriteAnimeEntity>> {
        return dao.getAllFavorites()
    }

}