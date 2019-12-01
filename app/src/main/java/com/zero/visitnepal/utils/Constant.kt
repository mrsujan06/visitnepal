package com.zero.visitnepal.utils

import com.zero.visitnepal.BuildConfig

class Constant {
    companion object {
        const val BASE_URL = "https://maps.googleapis.com/"
        const val PLACE_URL = "maps/api/place/textsearch/json?"

        const val CITY_QUERY = "Top+Cities+In+Nepal"
        const val API_KEY = BuildConfig.ApiKey

        const val IMAGE_URL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=600&photoreference="
        const val IMAGE_KEY = "&key="
    }
}