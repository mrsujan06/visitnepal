package com.zero.visitnepal.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zero.visitnepal.model.PlacesResponse
import com.zero.visitnepal.model.PlacesResult
import com.zero.visitnepal.repository.PlacesRepository
import kotlinx.coroutines.*
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class CityViewModel @Inject constructor(val repository: PlacesRepository) : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _citiesListObservable = MutableLiveData<List<PlacesResult>>()
    val citiesListObservable: LiveData<List<PlacesResult>>
        get() = _citiesListObservable

    private val cityList = mutableListOf<PlacesResult>()

    fun getData(cityResponse: PlacesResponse) = coroutineScope.launch {
        try {
            cityList.addAll(cityResponse.results)
            val nextPageToken = cityResponse.nextPageToken
            delay(1500)
            val cities = nextPageToken?.let { repository.fetchNextPage(it) }
            cityList.addAll(cities!!.results)
            _citiesListObservable.value = cityList
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}