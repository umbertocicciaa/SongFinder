package com.main.songfinder.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.main.songfinder.logic.Repository
import com.main.songfinder.logic.dao.Hits
import com.main.songfinder.logic.dao.SearchResponse

class SearchViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val responseList = ArrayList<Hits>()

    val searchResponseLiveData = searchLiveData.switchMap {
            query -> Repository.searchResponse(query)
    }
    fun searchResponses(query: String) {
        searchLiveData.value = query
    }
}