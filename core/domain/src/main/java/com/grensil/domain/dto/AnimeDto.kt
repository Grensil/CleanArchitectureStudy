package com.grensil.domain.dto

data class AnimeDto(
    var anime_id: Int? = null,
    var anime_name: String? = null,
    var anime_img: String? = null,
    var bookmarked: Boolean = false
)