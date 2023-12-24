package com.main.songfinder.ui.artist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.main.songfinder.logic.Repository

class ArtistViewModel : ViewModel() {

    private val idArtistLiveData = MutableLiveData<String>()
    val artistLiveData = idArtistLiveData.switchMap { artistId ->
        Repository.searchArtist(artistId)
    }

    fun refreshArtist(artistId: String) {
        idArtistLiveData.value = artistId
    }
}