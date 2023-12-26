package com.main.songfinder.ui.song

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.main.songfinder.logic.Repository
import com.main.songfinder.logic.dao.song.SongResponse

/**
 * Questa classe fornisce si occupa ad implementare il viewmodel relativo alle canzoni ricercate dall'utente
 * @author umbertodomenicociccias
 * */
class SongViewModel : ViewModel() {
    private val idSongLiveData = MutableLiveData<String>()

    val songLiveData = idSongLiveData.switchMap { songId ->
        Repository.searchSong(songId)
    }

    fun refreshSongId(id: String) {
        idSongLiveData.value = id
    }

    /**
     *  Questi tre metodi che incapsulano le funzionalita di salvataggio delle canzoni ricercate
     *  @author umbertodomenicociccia
     * */
    fun saveSong(song: SongResponse) = Repository.saveSong(song)
    fun getSavedSong(songId:String) = Repository.getSavedSong(songId)
    fun isSongSaved(songId:String) = Repository.isSongSaved(songId)
}