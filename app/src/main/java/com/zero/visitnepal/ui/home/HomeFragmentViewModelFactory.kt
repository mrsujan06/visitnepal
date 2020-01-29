package com.zero.visitnepal.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.visitnepal.repository.PlacesRepository

@Suppress("UNCHECKED_CAST")
class HomeFragmentViewModelFactory(val repository: PlacesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(repository) as T
    }
}