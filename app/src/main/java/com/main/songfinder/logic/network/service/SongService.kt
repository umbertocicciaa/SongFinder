package com.main.songfinder.logic.network.service

import com.main.songfinder.SongFinderApplication
import com.main.songfinder.logic.dao.song.SongResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/**
 * Questa interfaccia gestisce le get request relativo alle canzoni ricercate dall'utente
 * @author umbertodomenicociccia
 * */
interface SongService {
    @GET("songs/{id}")
    fun searchSong(
        @Path("id") songId: String,
        @Query("access_token") token: String = SongFinderApplication.TOKEN
    ): Call<SongResponse>
}