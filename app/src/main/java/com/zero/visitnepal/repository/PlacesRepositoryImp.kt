package com.zero.visitnepal.repository

import com.zero.visitnepal.model.PlacesResponse
import com.zero.visitnepal.remote.PlacesService
import com.zero.visitnepal.utils.Constant

class PlacesRepositoryImp(private val placesService: PlacesService) : PlacesRepository {
    override suspend fun fetchCities(): PlacesResponse {
        return placesService.fetchPlaces(Constant.CITY_QUERY, Constant.API_KEY)
    }

    override suspend fun fetchAttractions(): PlacesResponse {
        return placesService.fetchPlaces(Constant.TOP_ATTRACTION_QUERY, Constant.API_KEY)
    }

    override suspend fun fetchMountains(): PlacesResponse {
        return placesService.fetchPlaces(Constant.MOUNTAIN_QUERY, Constant.API_KEY)
    }

    override suspend fun fetchTemples(): PlacesResponse {
        return placesService.fetchPlaces(Constant.TEMPLE_QUERY, Constant.API_KEY)
    }

    override suspend fun fetchNextPage(token: String): PlacesResponse {
        return placesService.fetchPlacesUsingToken(token, Constant.API_KEY)
    }

}