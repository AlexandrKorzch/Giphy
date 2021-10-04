package com.test.korzh.giphyassignment.data.source.local

import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {

    val observeFavorites: Flow<List<Giphy>>

    suspend fun getGiphyByUrl(url: String): Giphy?

    fun update(giphy: Giphy)

    fun deleteFromFavorites(giphy: Giphy): Int
}