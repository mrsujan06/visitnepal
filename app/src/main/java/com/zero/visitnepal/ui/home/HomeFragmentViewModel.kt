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

    private val _cityObservable = MutableLiveData<PlacesResponse>()
    val cityObservable: LiveData<PlacesResponse>
        get() = _cityObservable

    private val _attractionObservable = MutableLiveData<PlacesResponse>()
    val attractionObservable: LiveData<PlacesResponse>
        get() = _attractionObservable

    private val _mountainObservable = MutableLiveData<PlacesResponse>()
    val mountainObservable: LiveData<PlacesResponse>
        get() = _mountainObservable

    private val _templeObservable = MutableLiveData<PlacesResponse>()
    val templeObservable: LiveData<PlacesResponse>
        get() = _templeObservable

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // fetch places data
    fun fetchPlacesData() = coroutineScope.launch {
        try {
            observeTenPlaces(_cityObservable, repository.fetchCities()) //cities
            observeTenPlaces(_attractionObservable, repository.fetchAttractions()) //attraction
            observeTenPlaces(_mountainObservable, repository.fetchMountains()) //mountains
            observeTenPlaces(_templeObservable, repository.fetchTemples()) //temples
        } catch (networkError: IOException) {
            Timber.e(networkError)
        }
    }

    //  Filter the list of results to 10, and hold it in live data
    private fun observeTenPlaces(
        placesObservable: MutableLiveData<PlacesResponse>,
        placesResponse: PlacesResponse

    ) {
        placesResponse.apply {
            this.results = this.results.take(10)
            placesObservable.value = this
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}