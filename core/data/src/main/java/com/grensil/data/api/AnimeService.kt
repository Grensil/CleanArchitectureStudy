package com.grensil.data.api

import com.grensil.data.model.AnimeListResponse
import retrofit2.http.GET

interface AnimeService {

    @GET("anime")
    suspend fun getAnimeList(): AnimeListResponse
}