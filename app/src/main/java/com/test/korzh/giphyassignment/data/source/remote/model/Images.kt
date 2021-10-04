package com.test.korzh.giphyassignment.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val original: Original,
    val original_still: OriginalStill
)