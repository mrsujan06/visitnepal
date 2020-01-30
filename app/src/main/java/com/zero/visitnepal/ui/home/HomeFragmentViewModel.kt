package com.zero.visitnepal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zero.visitnepal.model.PlacesResponse
import com.zero.visitnepal.repository.PlacesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(val repository: PlacesRepository) : ViewModel() {

    private val _placesList = MutableLiveData<PlacesResponse>()
    val placesList: LiveData<PlacesResponse>
        get() = _placesList

    private val _attractionList = MutableLiveData<PlacesResponse>()
    val attractionList: LiveData<PlacesResponse>
        get() = _attractionList

    private val _mountainList = MutableLiveData<PlacesResponse>()
    val mountainList: LiveData<PlacesResponse>
        get() = _mountainList

    private val _templeList = MutableLiveData<PlacesResponse>()
    val templeList: LiveData<PlacesResponse>
        get() = _templeList

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //fetch cities data
    fun getCitiesData() = coroutineScope.launch {
        try {
            val cities = repository.fetchCities()
            _placesList.value = cities
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    // fetch attraction data
    fun getAttractionData() = coroutineScope.launch {
        try {
            val attractions = repository.fetchAttractions()
            _attractionList.value = attractions
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    //     fetch mountain data
    fun getMountainsData() = coroutineScope.launch {
        try {
            val mountains = repository.fetchMountains()
            mountains.results.take(12)
            _mountainList.value = mountains
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    //     fetch temple data
    fun getTemplesData() = coroutineScope.launch {
        try {
            val temples = repository.fetchTemples()
            _templeList.value = temples
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}