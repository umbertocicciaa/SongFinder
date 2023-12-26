package com.main.songfinder.logic.network.service

import com.main.songfinder.SongFinderApplication
import com.main.songfinder.logic.dao.search.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Questa interfaccia gestisce le get request relativo alla ricerca effettuata dall'utente
 * @author umbertodomenicociccia
 * */
interface SearchService {
    @GET("search")
    fun searchResponse(
        @Query("q") name: String,
        @Query("access_token") token: String = SongFinderApplication.TOKEN
    ): Call<SearchResponse>

}