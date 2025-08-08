package com.grensil.data.local.database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.grensil.data.local.entity.FavoriteAnimeEntity

class TypeConverter {

    @androidx.room.TypeConverter
    fun fromAnimeList(list: List<FavoriteAnimeEntity>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    @androidx.room.TypeConverter
    fun toAnimeList(json: String): List<FavoriteAnimeEntity> {
        val gson = Gson()
        return gson.fromJson(json, object : TypeToken<List<FavoriteAnimeEntity>>() {}.type)
    }
}