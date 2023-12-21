package com.main.songfinder.logic.dao

import com.google.gson.annotations.SerializedName

data class SongResponse(val meta: Meta, val response: ResponseSong)
data class ResponseSong(val song: Song)
data class Song(
    @SerializedName("song_art_image_thumbnail_url")
    val imageUrl: String,
    val title: String,
    @SerializedName("release_date_for_display")
    val release: String,
    val url: String,
    @SerializedName("artist_names")
    val artits: String,
    @SerializedName("lyrics_owner_id")
    val artisId: Int,
    val album: Album
)

data class Album(
    @SerializedName("lyrics_owner_id") val imageUrl: String,
    val url: String
)

