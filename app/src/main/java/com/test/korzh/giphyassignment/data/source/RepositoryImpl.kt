package com.test.korzh.giphyassignment.data.source

import com.test.korzh.giphyassignment.data.source.local.LocalDataSource
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import com.test.korzh.giphyassignment.data.source.remote.RemoteDataSource
import com.test.korzh.giphyassignment.data.source.util.convertToList
import kotlinx.coroutines.flow.Flow


class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getGiphyTrending(offset: Int): List<Giphy> {
        val giphyTrend = remoteDataSource.getGiphyTrend(offset)
        return convertToList(giphyTrend, localDataSource)
    }

    override suspend fun getGiphySearch(offset: Int, searchText: String?): List<Giphy> {
        val searchResult = remoteDataSource.getGiphySearch(offset, searchText)
        return convertToList(searchResult, localDataSource)
    }

    override fun changeState(giphy: Giphy) {
        localDataSource.update(giphy)
    }

    override fun observeFavorites(): Flow<List<Giphy>> = run {
        localDataSource.observeFavorites
    }

    override fun deleteFromFavorites(giphy: Giphy) {
        localDataSource.deleteFromFavorites(giphy)
    }
}