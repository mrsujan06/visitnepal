package com.zero.visitnepal.model

import com.squareup.moshi.Json

data class Result(
    @Json(name = "formatted_address")
    val formattedAddress: String,
    @Json(name = "geometry")
    val geometry: Geometry,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "photos")
    val photos: List<Photo>,
    @Json(name = "place_id")
    val placeId: String,
    @Json(name = "reference")
    val reference: String,
    @Json(name = "types")
    val types: List<String>
)