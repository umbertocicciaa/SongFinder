package com.main.songfinder.logic.dao.song

import com.google.gson.annotations.SerializedName
import com.main.songfinder.logic.dao.search.Meta

/**
 * Questa classe incapsula il risultato della get request di song
 * @author umbertodomenicociccia
 * */

data class SongResponse(val meta: Meta, val response: ResponseSong)
data class ResponseSong(val song: Song)
data class Song(
    @SerializedName("song_art_image_thumbnail_url")
    val imageUrl: String,
    val title: String,
    @SerializedName("release_date_for_display")
    val release: String,
    //genius url
    val url: String?,
    @SerializedName("artist_names")
    val artits: String,
    @SerializedName("lyrics_owner_id")
    val artisId: String,
    val album: Album?,
    val id: String,
    @SerializedName("primary_artist")
    val artist: ArtistInfo
)

data class Album(
    @SerializedName("cover_art_url")
    val imageUrl: String?,
    //genius url
    val url: String?
)

data class ArtistInfo(
    val id: String
)

