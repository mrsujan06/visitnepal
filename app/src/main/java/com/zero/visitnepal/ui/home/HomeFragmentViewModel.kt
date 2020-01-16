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

    private val _atractionList = MutableLiveData<PlacesResponse>()
    val attractionList: LiveData<PlacesResponse>
        get() = _atractionList

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getData() = coroutineScope.launch {
        try {
            val cities = repository.fetchPlaces()
            _placesList.value = cities
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    //fetch attraction data
    fun getAttractionData() = coroutineScope.launch {
        try {
            val attractions = repository.fetchAttractions()
            _atractionList.value = attractions
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}