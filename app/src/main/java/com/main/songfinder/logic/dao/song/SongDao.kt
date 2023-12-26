package com.main.songfinder.logic.dao.song

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.main.songfinder.SongFinderApplication

/**
 * Questo singleton si occupa di incapsulare tutti metodi necessari a salvare le canzoni ricercate dall'utente
 * @author umbertodomenicociccia
 * */
object SongDao {
    fun saveSong(song: SongResponse) {
        sharedPreferences().edit {
            putString(song.response.song.id, Gson().toJson(song))
        }
    }
    fun getSavedSong(songId: String): SongResponse {
        val song = sharedPreferences().getString(songId, "")
        return Gson().fromJson(song, SongResponse::class.java)
    }

    fun isSongSaved(songId: String) = sharedPreferences().contains(songId)
    private fun sharedPreferences() =
        SongFinderApplication.context.getSharedPreferences("song_finder", Context.MODE_PRIVATE)

}