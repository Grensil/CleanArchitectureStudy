package com.grensil.data.mapper


import com.grensil.data.model.AnimeItem
import com.grensil.data.model.AnimeListResponse
import com.grensil.domain.entity.AnimeEntity


fun AnimeItem.toAnimeEntity(): AnimeEntity {
    return AnimeEntity(
        anime_id = this.id,
        anime_name = this.title,
        anime_img = this.images.jpg.imageUrl
    )
}