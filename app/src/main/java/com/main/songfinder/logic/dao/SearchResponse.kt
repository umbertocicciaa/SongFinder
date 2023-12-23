package com.main.songfinder.logic.dao

import com.google.gson.annotations.SerializedName

/**
 * Questa classe incapsula il risultato della get request di ricerca
 * @author umbertodomenicociccia
* */

data class SearchResponse(val meta: Meta, val response: Response)
data class Meta(val status: Int)
data class Response(val hits: List<Hits>)
data class Hits(val result: Result)
data class Result(
    @SerializedName("artist_names")
    val nameArtist: String,
    @SerializedName("full_title")
    val title: String,
    @SerializedName("id")
    val songId: String,
    @SerializedName("lyrics_owner_id")
    val lyrycsOwnerId: String,
    @SerializedName("song_art_image_thumbnail_url")
    val imageUrl: String
)