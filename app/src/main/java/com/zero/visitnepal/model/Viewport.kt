package com.zero.visitnepal.model

import com.squareup.moshi.Json

data class Viewport(
    @Json(name = "northeast")
    val northeast: Northeast,
    @Json(name = "southwest")
    val southwest: Southwest
)