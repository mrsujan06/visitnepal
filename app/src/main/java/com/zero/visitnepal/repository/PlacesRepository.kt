package com.zero.visitnepal.repository

import com.zero.visitnepal.model.PlacesResponse

interface PlacesRepository {
    suspend fun fetchPlaces(): PlacesResponse
}