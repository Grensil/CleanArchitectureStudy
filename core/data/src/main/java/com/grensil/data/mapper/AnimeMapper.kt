package com.grensil.data.mapper

import com.grensil.data.model.AnimeData
import com.grensil.data.model.AnimeListResponse
import com.grensil.domain.entity.AnimeEntity


fun AnimeData.toAnimeEntity(): AnimeEntity {

    return AnimeEntity(
        anime_id = this.anime_id,
        anime_name = this.anime_name,
        anime_img = this.anime_img
    )
}