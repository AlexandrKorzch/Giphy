package com.test.korzh.giphyassignment.data.source.remote

import com.test.korzh.giphyassignment.data.source.remote.model.GiphyResult


interface RemoteDataSource {

    suspend fun getGiphyTrend(offset: Int): GiphyResult?

    suspend fun getGiphySearch(offset: Int, searchText: String?): GiphyResult?
}