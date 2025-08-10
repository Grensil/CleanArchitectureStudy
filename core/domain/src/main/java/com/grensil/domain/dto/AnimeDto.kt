package com.grensil.domain.dto

data class AnimeDto(
    var anime_id: Int,
    var anime_name: String,
    var anime_img: String? = null,
    var bookmarked: Boolean? = null
)