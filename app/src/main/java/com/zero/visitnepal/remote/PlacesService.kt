package com.zero.visitnepal.remote

import com.zero.visitnepal.model.PlacesResponse
import com.zero.visitnepal.utils.Constant
import com.zero.visitnepal.utils.RetrofitServiceProvider
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesService {
    companion object {
        val instance: PlacesService by lazy {
            RetrofitServiceProvider.retrofit.create(PlacesService::class.java)
        }
    }

    @GET(Constant.PLACE_URL)
    suspend fun fetchPlaces(@Query("query") query: String, @Query("key") key: String): PlacesResponse
}


