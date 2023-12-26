package com.main.songfinder.ui.artist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.main.songfinder.logic.Repository
import com.main.songfinder.logic.dao.artist.ArtistResponse

/**
 *  Questa classe si occupa di fornire il viewmodel per quanto riguarda la visualizzaizone degli artisti
 *  @author umbertodomenicociccia
 * */
class ArtistViewModel : ViewModel() {

    private val idArtistLiveData = MutableLiveData<String>()
    val artistLiveData = idArtistLiveData.switchMap { artistId ->
        Repository.searchArtist(artistId)
    }

    fun refreshArtist(artistId: String) {
        idArtistLiveData.value = artistId
    }

    /**
     *  Questi tre metodi che incapsulano le funzionalita di salvataggio degli artisti ricercati
     *  @author umbertodomenicociccia
     * */
    fun saveArtist(artist: ArtistResponse) = Repository.saveArtist(artist)
    fun getSavedArtist(artistId: String) = Repository.getSavedArtist(artistId)
    fun isArtistSaved(artistId: String) = Repository.isArtistSaved(artistId)
}