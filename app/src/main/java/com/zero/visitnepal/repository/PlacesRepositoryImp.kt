package com.zero.visitnepal.repository

import com.zero.visitnepal.model.PlacesResponse
import com.zero.visitnepal.remote.PlacesService
import com.zero.visitnepal.utils.Constant

class PlacesRepositoryImp(private val webService: PlacesService) : PlacesRepository {
    override suspend fun fetchPlaces(): PlacesResponse {
        return webService.fetchPlaces(Constant.CITY_QUERY, Constant.API_KEY)
    }
}