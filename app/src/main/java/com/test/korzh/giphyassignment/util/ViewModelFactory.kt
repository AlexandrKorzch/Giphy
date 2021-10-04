package com.test.korzh.giphyassignment.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.test.korzh.giphyassignment.data.source.Repository
import com.test.korzh.giphyassignment.ui.fragment.favorite.FavoriteViewModel
import com.test.korzh.giphyassignment.ui.fragment.search.SearchViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val repository: Repository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(SearchViewModel::class.java) ->
                SearchViewModel(repository)
            isAssignableFrom(FavoriteViewModel::class.java) ->
                FavoriteViewModel(repository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}