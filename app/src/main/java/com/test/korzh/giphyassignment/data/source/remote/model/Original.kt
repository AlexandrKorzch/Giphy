package com.test.korzh.giphyassignment.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Original(
    val height: String,
    val url: String
)