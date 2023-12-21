package com.main.songfinder.logic.dao

import com.google.gson.annotations.SerializedName

data class SearchResponse(val meta: Meta, val response: Response)
data class Meta(val status: Int)
data class Response(val hits: List<Hits>)
data class Hits(val result: Result)
data class Result(
    @SerializedName("artist_names")
    val nameArtist: String,
    @SerializedName("full_title")
    val title: String,
    val id: Int
)