package com.grensil.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_anime")
data class FavoriteAnimeEntity(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id" )val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "thumbnailUrl") val thumbnailUrl: String,
    @ColumnInfo(name = "bookmarked") val bookmarked : Boolean,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "addedAt") val addedAt: Long = System.currentTimeMillis()
)