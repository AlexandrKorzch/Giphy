package com.test.korzh.giphyassignment.ui.fragment.favorite

import androidx.lifecycle.*
import com.test.korzh.giphyassignment.data.source.Repository
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavoriteViewModel(private val repository: Repository) : ViewModel() {

    val favorites: LiveData<List<Giphy>> = repository.observeFavorites().asLiveData()

    fun deleteGiphy(giphy: Giphy) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFromFavorites(giphy)
        }
    }
}