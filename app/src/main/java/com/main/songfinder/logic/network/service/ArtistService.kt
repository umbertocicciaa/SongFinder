package com.main.songfinder.logic.network.service

import com.main.songfinder.SongFinderApplication
import com.main.songfinder.logic.dao.artist.ArtistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Questa interfaccia gestisce le get request relativo alla ricerca effettuata dall'utente relativa ad un artista
 * @author umbertodomenicociccia
 * */
interface ArtistService {
    @GET("artists/{id}")
    fun searchArtist(
        @Path("id") artistId: String,
        @Query("access_token") token: String = SongFinderApplication.TOKEN
    ): Call<ArtistResponse>
}