package com.zero.visitnepal.model

import com.squareup.moshi.Json

data class Geometry(
    @Json(name = "location")
    val location: Location,
    @Json(name = "viewport")
    val viewport: Viewport
)