package com.test.korzh.giphyassignment.data.source.remote

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class RemoteDataSourceTest {

    private var remoteDataSourceImpl: RemoteDataSource? = null

    @Before
    fun setUp() {
        remoteDataSourceImpl = RemoteDataSourceImpl(ktorHttpClient)
    }

    @Test
    fun getGiphyTrend() = runBlocking {
        val giphyResult = remoteDataSourceImpl?.getGiphyTrend(0)
        assertNotNull(giphyResult)
        assert(giphyResult?.data?.size?.let { it > 0 } ?: false)
        print(giphyResult)
    }

    @Test
    fun getGiphySearch() = runBlocking {
        val giphyResult = remoteDataSourceImpl?.getGiphySearch(0, "cat")
        assertNotNull(giphyResult)
        assert(giphyResult?.data?.size?.let { it > 0 } ?: false)
    }
}