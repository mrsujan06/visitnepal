package com.zero.visitnepal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.visitnepal.repository.PlacesRepository

@Suppress("UNCHECKED_CAST")
class CityViewModelFactory(val repository: PlacesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CityViewModel(repository) as T
    }
}