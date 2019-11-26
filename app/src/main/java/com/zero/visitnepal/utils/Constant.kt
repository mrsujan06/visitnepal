package com.zero.visitnepal.utils

import com.zero.visitnepal.BuildConfig

class Constant {
    companion object {
        const val BASE_URL = "https://maps.googleapis.com/"
        const val PLACE_URL = "maps/api/place/textsearch/json?"

        const val CITY_QUERY = "Top+cities+in+nepal"
        const val API_KEY = BuildConfig.ApiKey
    }
}