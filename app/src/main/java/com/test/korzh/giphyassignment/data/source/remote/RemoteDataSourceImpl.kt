package com.test.korzh.giphyassignment.data.source.remote

import com.test.korzh.giphyassignment.data.source.remote.model.GiphyResult
import io.ktor.client.*
import io.ktor.client.request.*


class RemoteDataSourceImpl internal constructor(
    private val client: HttpClient
) : RemoteDataSource {

    private val scheme = "https://"
    private val baseUrl = "api.giphy.com/v1/gifs"
    private val trending = "/trending"
    private val search = "/search"
    private val params = "?"
    private val apiKey = "jAPjq71WZOULIwUhysmBGMpucNECuXzP"
    private val pageLimit = 10

    override suspend fun getGiphyTrend(offset: Int): GiphyResult {
        val trendingUrl = "$scheme$baseUrl$trending" +
                params +
                "api_key=$apiKey" +
                "&limit=$pageLimit" +
                "&offset=$offset"
        return client.get(trendingUrl)
    }

    override suspend fun getGiphySearch(offset: Int, searchText: String?): GiphyResult {
        val searchUrl = "$scheme$baseUrl$search" +
                params +
                "api_key=$apiKey" +
                "&limit=$pageLimit" +
                "&offset=$offset" +
                "&q=$searchText"
        return client.get(searchUrl)
    }
}