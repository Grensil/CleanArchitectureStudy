package com.grensil.data.model

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    val data: List<AnimeItem>
)

data class AnimeItem(
    @SerializedName("mal_id") val id: Int,
    val title: String,
    val images: AnimeImages
)

data class AnimeImages(
    val jpg: AnimeJpg
)

data class AnimeJpg(
    @SerializedName("image_url") val imageUrl: String
)