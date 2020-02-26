package com.zero.visitnepal.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zero.visitnepal.model.PlacesResult
import com.zero.visitnepal.repository.PlacesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class CityViewModel @Inject constructor(val repository: PlacesRepository) : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _citiesListObservable = MutableLiveData<List<PlacesResult>>()
    val citiesListObservable: LiveData<List<PlacesResult>>
        get() = _citiesListObservable

    private val cityList =mutableListOf<PlacesResult>()
    private var nextPageToken: String?  = null

    fun getData() = coroutineScope.launch {
        try {
            val cities = repository.fetchCities()
            nextPageToken = cities.nextPageToken
            cityList.addAll(cities.results)
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    fun getDataUsingToken() = coroutineScope.launch {
        try {
            val cities = repository.fetchNextPage(nextPageToken)
            cityList.addAll(cities.results)
            _citiesListObservable.value= cityList
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}