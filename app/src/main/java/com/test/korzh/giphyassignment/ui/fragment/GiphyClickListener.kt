package com.test.korzh.giphyassignment.ui.fragment

import com.test.korzh.giphyassignment.data.source.local.model.Giphy

interface GiphyClickListener {
    fun onFavoriteClick(giphy: Giphy, isFavorite : Boolean)
}