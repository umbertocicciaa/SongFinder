package com.main.songfinder.logic

import androidx.lifecycle.liveData
import com.main.songfinder.SongFinderApplication
import com.main.songfinder.logic.dao.SongResponse
import com.main.songfinder.logic.network.SongFinderNetwork
import kotlinx.coroutines.Dispatchers

object Repository {
    fun searchResponse(name: String) = liveData(Dispatchers.IO) {
        val result = try {
            val searchResponse = SongFinderNetwork.searchResponse(name)
            if (searchResponse.meta.status == SongFinderApplication.RESPONSE_OK) {
                Result.success(searchResponse)
            } else {
                Result.failure(RuntimeException("response status is ${searchResponse.meta.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun searchSong(id: Int) = liveData(Dispatchers.IO) {
        val result = try {
            val songResponse: SongResponse = SongFinderNetwork.searchSong(id)
            if (songResponse.meta.status == SongFinderApplication.RESPONSE_OK) {
                Result.success(songResponse)
            } else {
                Result.failure(RuntimeException("response status is ${songResponse.meta.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

}