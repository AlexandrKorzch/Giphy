package com.test.korzh.giphyassignment.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class GiphyResult(
    val data: List<Data>? = null
)