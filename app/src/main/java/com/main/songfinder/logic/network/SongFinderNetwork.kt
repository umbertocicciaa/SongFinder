package com.main.songfinder.logic.network

import com.main.songfinder.logic.dao.SearchResponse
import com.main.songfinder.logic.dao.SongResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SongFinderNetwork {

    private val searchService = ServiceCreator.create(SearchService::class.java)
    private val songService = ServiceCreator.create(SongService::class.java)
    suspend fun searchResponse(name: String): SearchResponse =
        searchService.searchResponse(name).await()
    suspend fun searchSong(id: Int): SongResponse =
        songService.searchSong(id).await()
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body) else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}

