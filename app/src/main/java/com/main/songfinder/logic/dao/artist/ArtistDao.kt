package com.main.songfinder.logic.dao.artist

import android.content.Context
import com.google.gson.Gson
import com.main.songfinder.SongFinderApplication
import androidx.core.content.edit

/**
 * Questo singleton si occupa di incapsulare tutti metodi necessari a salvare gli artisti ricercati dall'utente
 * @author umbertodomenicociccia
 * */
object ArtistDao {
    fun saveArtist(artist: ArtistResponse) {
        sharedPreferences().edit {
            putString(artist.response.artist.id, Gson().toJson(artist))
        }
    }

    fun getSavedArtist(artistId: String): ArtistResponse {
        val artist = sharedPreferences().getString(artistId, "")
        return Gson().fromJson(artist, ArtistResponse::class.java)
    }

    fun isArtistSaved(artistId: String) = sharedPreferences().contains(artistId)
    private fun sharedPreferences() =
        SongFinderApplication.context.getSharedPreferences("song_finder", Context.MODE_PRIVATE)
}