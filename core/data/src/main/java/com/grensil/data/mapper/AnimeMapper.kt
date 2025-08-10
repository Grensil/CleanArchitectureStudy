package com.grensil.data.mapper


import com.grensil.data.local.entity.FavoriteAnimeEntity
import com.grensil.data.model.AnimeEntity
import com.grensil.domain.dto.AnimeDto

/** 백엔드 -> 로컬 비즈니스 로직 **/
fun AnimeEntity.toAnimeDto(): AnimeDto {
    return AnimeDto(
        anime_id = this.id,
        anime_name = this.title,
        anime_img = this.images.jpg.imageUrl
    )
}

/** 로컬 entity -> 로컬 비즈니스 로직 dto **/
fun FavoriteAnimeEntity.toAnimeDto(): AnimeDto {
    return AnimeDto(
        anime_id = this.animeId,
        anime_name = this.name,
        anime_img = this.anime_img,
        bookmarked = this.bookmarked
    )
}

//로컬 비즈니스 로직 -> 로컬 entity
fun AnimeDto.toFavoriteAnimeEntity(): FavoriteAnimeEntity {
    return FavoriteAnimeEntity(
        animeId = this.anime_id,
        name = this.anime_name,
        anime_img = this.anime_img?: "",
        bookmarked = this.bookmarked?: true /** 없으면 삭제 했기 때문에 존재하면 true **/
    )
}