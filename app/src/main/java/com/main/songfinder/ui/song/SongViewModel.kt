package com.main.songfinder.ui.song

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.main.songfinder.logic.Repository

class SongViewModel : ViewModel() {
    private val idSongLiveData = MutableLiveData<String>()
    
    val songLiveData = idSongLiveData.switchMap { songId ->
        Repository.searchSong(songId)
    }
    fun refreshSongId(id: String) {
        idSongLiveData.value = id
    }
}