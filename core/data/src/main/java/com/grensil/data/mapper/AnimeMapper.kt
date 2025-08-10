package com.grensil.data.mapper


import com.grensil.data.local.entity.FavoriteAnimeEntity
import com.grensil.data.model.AnimeItem
import com.grensil.domain.dto.AnimeDto
import com.grensil.domain.dto.FavoriteAnimeDto

fun AnimeItem.toAnimeDto(): AnimeDto {
    return AnimeDto(
        anime_id = this.id,
        anime_name = this.title,
        anime_img = this.images.jpg.imageUrl
    )
}

fun FavoriteAnimeEntity.toAnimeDto(): AnimeDto {
    return AnimeDto(
        anime_id = this.id,
        anime_name = this.name,
        bookmarked = this.bookmarked
    )
}