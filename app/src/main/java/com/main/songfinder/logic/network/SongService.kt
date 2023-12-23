package com.main.songfinder.logic.network

import com.main.songfinder.SongFinderApplication
import com.main.songfinder.logic.dao.SongResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface SongService {
    @GET("songs/{id}")
    fun searchSong(
        @Path("id") songId: String,
        @Query("access_token") token: String = SongFinderApplication.TOKEN
    ): Call<SongResponse>
}