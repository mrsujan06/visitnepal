package com.zero.visitnepal.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zero.visitnepal.repository.PlacesRepository
import com.zero.visitnepal.utils.ConnectionChecker

@Suppress("UNCHECKED_CAST")
class HomeFragmentViewModelFactory(private val repository: PlacesRepository, private val connectionChecker: ConnectionChecker) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(repository, connectionChecker) as T
    }
}