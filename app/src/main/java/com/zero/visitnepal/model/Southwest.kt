package com.zero.visitnepal.model

import com.squareup.moshi.Json

data class Southwest(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lng")
    val lng: Double
)