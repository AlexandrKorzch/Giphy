package com.test.korzh.giphyassignment.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val images: Images,
    val title: String,
)