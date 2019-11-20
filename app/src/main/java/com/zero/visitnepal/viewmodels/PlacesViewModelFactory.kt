package com.zero.visitnepal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.visitnepal.repository.PlacesRepository

@Suppress("UNCHECKED_CAST")
class PlacesViewModelFactory(private val repository: PlacesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlacesViewModel(repository) as T
    }
}