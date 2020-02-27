package com.zero.visitnepal.repository

import com.zero.visitnepal.model.PlacesResponse

interface PlacesRepository {
    suspend fun fetchCities(): PlacesResponse
    suspend fun fetchAttractions(): PlacesResponse
    suspend fun fetchMountains(): PlacesResponse
    suspend fun fetchTemples(): PlacesResponse
    suspend fun fetchNextPage(token: String): PlacesResponse
}