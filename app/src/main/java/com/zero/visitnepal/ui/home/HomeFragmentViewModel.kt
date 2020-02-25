package com.zero.visitnepal.ui.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zero.visitnepal.model.PlacesResponse
import com.zero.visitnepal.repository.PlacesRepository
import com.zero.visitnepal.utils.ConnectionChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
    private val repository: PlacesRepository,
    private val connectionChecker: ConnectionChecker
) : ViewModel() {

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

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // fetch places data
    fun fetchPlacesData() = coroutineScope.launch {
        try {
            if (!connectionChecker.isOnline()) {
                _loadingState.value = LoadingState.ERROR
            } else {
                _loadingState.value = LoadingState.LOADING
                observeTenPlaces(_cityObservable, repository.fetchCities()) //cities
                observeTenPlaces(_attractionObservable, repository.fetchAttractions()) //attraction
                observeTenPlaces(_mountainObservable, repository.fetchMountains()) //mountains
                observeTenPlaces(_templeObservable, repository.fetchTemples()) //temples
                _loadingState.value = LoadingState.DONE
            }

        } catch (error: Error) {
            Timber.e(error)
            _loadingState.value = LoadingState.ERROR
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

    enum class LoadingState {
        LOADING,
        DONE,
        ERROR
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}