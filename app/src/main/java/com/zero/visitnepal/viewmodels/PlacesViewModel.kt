package com.zero.visitnepal.viewmodels

import androidx.lifecycle.ViewModel
import com.zero.visitnepal.repository.PlacesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class PlacesViewModel(private val repository: PlacesRepository) : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getData() = coroutineScope.launch {
        try {
            val cities = repository.fetchPlaces()
            Timber.d(cities.toString())
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}