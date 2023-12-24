package com.main.songfinder.logic.dao

import com.google.gson.annotations.SerializedName

/**
 * Questa classe incapsula il risultato della get request di artist
 * @author umbertodomenicociccia
 * */
data class ArtistResponse(val meta: Meta, val response: ResponseAlbum)
data class ResponseAlbum(val artist: Artist)
data class Artist(
    val id: String,
    @SerializedName("image_url")
    val urlImage: String,
    val url: String,
    val name: String
)