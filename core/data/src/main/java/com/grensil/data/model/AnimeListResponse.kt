package com.grensil.data.model

data class AnimeListResponse(
    var success : Boolean? = null,
    var data : List<AnimeData>? = null
)

data class AnimeData(
    var anime_id: Int? = null,
    var anime_name: String? = null,
    var anime_img: String? = null
)