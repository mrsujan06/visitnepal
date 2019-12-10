package com.zero.visitnepal.remote

import com.zero.visitnepal.model.PlacesResponse
import com.zero.visitnepal.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesService {
    @GET(Constant.PLACE_URL)
    suspend fun fetchPlaces(@Query("query") query: String, @Query("key") key: String): PlacesResponse
}
