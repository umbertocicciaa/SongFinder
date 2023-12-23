package com.main.songfinder.logic

import androidx.lifecycle.liveData
import com.main.songfinder.SongFinderApplication
import com.main.songfinder.logic.dao.SongDao
import com.main.songfinder.logic.dao.SongResponse
import com.main.songfinder.logic.network.SongFinderNetwork
import kotlinx.coroutines.Dispatchers

/**
 * Questo singleton fornisce i punti di accesso ai metodi di rete e ai dati
 * @author umbertodomenicociccia
 * */
object Repository {

    /**Il metodo effettuata una richiesta in rete data una stringa fornita dall'utente e ne associa il risultato al live data chiamante
     **/
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

    /**Il metodo effettuata una ricerca di canzone in rete data una stringa id fornita dall'utente e ne associa il risultato al live data chiamante
     **/
    fun searchSong(id: String) = liveData(Dispatchers.IO) {
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

    fun saveSong(song: SongResponse) = SongDao.saveSong(song)
    fun getSavedSong(songId: String) = SongDao.getSavedSong(songId)
    fun isSongSaved(songId: String) = SongDao.isSongSaved(songId)

}