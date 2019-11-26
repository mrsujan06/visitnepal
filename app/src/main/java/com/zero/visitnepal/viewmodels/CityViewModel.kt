package com.zero.visitnepal.viewmodels

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

class CityViewModel(private val repository: PlacesRepository) : ViewModel() {

    private val _citiesList = MutableLiveData<PlacesResponse>()
    val citiesList: LiveData<PlacesResponse>
        get() = _citiesList

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getData()
    }

    private fun getData() = coroutineScope.launch {
        try {
            val cities = repository.fetchPlaces()
            _citiesList.value = cities
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}