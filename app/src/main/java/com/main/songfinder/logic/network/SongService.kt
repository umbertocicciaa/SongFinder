package com.main.songfinder.logic.network

import com.main.songfinder.SongFinderApplication
import com.main.songfinder.logic.dao.SongResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface SongService {
    @GET("songs/")
    @Headers("${SongFinderApplication.HEADER}")
    fun searchSong(songId: Int): Call<SongResponse>
}