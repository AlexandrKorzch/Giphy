package com.test.korzh.giphyassignment.data.source.util

import com.test.korzh.giphyassignment.data.source.local.LocalDataSource
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import com.test.korzh.giphyassignment.data.source.remote.model.GiphyResult


internal suspend fun convertToList(giphyTrend: GiphyResult,
                                   localDataSource: LocalDataSource): ArrayList<Giphy> {
    return ArrayList<Giphy>().apply {
        giphyTrend.data.forEach {
            it.images.run {
                val gifUrl = original.url
                val giphy = localDataSource.getGiphyByUrl(gifUrl)
                val isFavorite = giphy?.isFavorite ?: false
                add(
                    Giphy(
                        title = it.title,
                        gifUrl = gifUrl,
                        stillUrl = original_still.url,
                        isFavorite = isFavorite,
                        height = original.height.toInt()
                    )
                )
            }
        }
    }
}