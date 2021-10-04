package com.test.korzh.giphyassignment.data.source

import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getGiphyTrending(offset: Int): List<Giphy>

    suspend fun getGiphySearch(offset: Int, searchText: String?): List<Giphy>?

    fun changeState(giphy: Giphy)

    fun observeFavorites(): Flow<List<Giphy>>

    fun deleteFromFavorites(giphy: Giphy)
}