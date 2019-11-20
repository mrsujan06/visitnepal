package com.zero.visitnepal.model

import com.squareup.moshi.Json

data class Photo(
    @Json(name = "height")
    val height: Int,
    @Json(name = "html_attributions")
    val htmlAttributions: List<String>,
    @Json(name = "photo_reference")
    val photoReference: String,
    @Json(name = "width")
    val width: Int
)