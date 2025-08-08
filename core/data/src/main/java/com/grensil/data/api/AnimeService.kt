package com.grensil.data.api

import com.grensil.data.model.remote.AnimeListResponse
import retrofit2.http.GET

interface AnimeService {

    @GET("anime")
    suspend fun getAnimeList(): AnimeListResponse
}