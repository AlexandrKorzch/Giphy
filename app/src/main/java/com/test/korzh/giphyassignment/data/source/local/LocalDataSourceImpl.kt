package com.test.korzh.giphyassignment.data.source.local

import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl internal constructor(
    private val giphyDao: GiphyDao
) : LocalDataSource {

    override val observeFavorites: Flow<List<Giphy>> = giphyDao.observeFavorites()

    override fun update(giphy: Giphy) {
        if (giphy.isFavorite) {
            giphyDao.insertGiphy(giphy)
        } else {
            giphyDao.deleteFromFavorites(giphy.gifUrl)
        }
    }

    override suspend fun getGiphyByUrl(url: String): Giphy? =
        giphyDao.getGiphyByUrl(url)

    override fun deleteFromFavorites(giphy: Giphy) =
        giphyDao.deleteFromFavorites(giphy.gifUrl)
}