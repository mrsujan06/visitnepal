package com.zero.visitnepal.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class PlacesResponse(
    @Json(name = "html_attributions")
    val htmlAttributions: @RawValue List<Any>,
    @Json(name = "next_page_token")
    val nextPageToken: String?,
    @Json(name = "results")
    var results: @RawValue List<PlacesResult>,
    @Json(name = "status")
    val status: String
) : Parcelable