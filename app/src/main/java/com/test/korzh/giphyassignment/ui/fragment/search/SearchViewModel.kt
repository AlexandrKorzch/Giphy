package com.test.korzh.giphyassignment.ui.fragment.search

import android.content.Context
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.*
import com.test.korzh.giphyassignment.data.source.Repository
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import com.test.korzh.giphyassignment.ui.util.LoadFileCallback
import com.test.korzh.giphyassignment.ui.util.loadFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchViewModel(private val repository: Repository) : ViewModel() {

    val progressLiveData: MutableLiveData<Int> = MutableLiveData()
    val giphyTrendLiveData: MutableLiveData<List<Giphy>> = MutableLiveData()
    val giphySearchLiveData: MutableLiveData<List<Giphy>> = MutableLiveData()

    val favorites: LiveData<List<Giphy>> = repository.observeFavorites().asLiveData()

    fun getGiphyTrending(offset: Int) {
        showProgress()
        viewModelScope.launch(Dispatchers.IO) {
            giphyTrendLiveData.postValue(repository.getGiphyTrending(offset))
            hideProgress()
        }
    }

    fun getGiphySearch(offset: Int, searchText: String?) {
        showProgress()
        viewModelScope.launch(Dispatchers.IO) {
            giphySearchLiveData.postValue(repository.getGiphySearch(offset, searchText))
            hideProgress()
        }
    }

    fun changeFileState(context: Context?, giphy: Giphy, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            loadFile(context, giphy, isFavorite, object : LoadFileCallback {
                override fun onLoaded(giphy: Giphy) {
                    repository.changeState(giphy)
                }
            })
        }
    }

    private fun showProgress() {
        progressLiveData.postValue(VISIBLE)
    }

    private fun hideProgress() {
        progressLiveData.postValue(GONE)
    }
}