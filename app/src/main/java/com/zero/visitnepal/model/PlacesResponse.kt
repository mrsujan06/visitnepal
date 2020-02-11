package com.zero.visitnepal.model

import com.squareup.moshi.Json

data class PlacesResponse(
    @Json(name = "html_attributions")
    val htmlAttributions: List<Any>,
    @Json(name = "next_page_token")
    val nextPageToken: String?,
    @Json(name = "results")
    var results: List<Result>,
    @Json(name = "status")
    val status: String
)