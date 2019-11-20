package com.zero.visitnepal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.zero.visitnepal.remote.PlacesService
import com.zero.visitnepal.repository.PlacesRepositoryImp
import com.zero.visitnepal.viewmodels.PlacesViewModel
import com.zero.visitnepal.viewmodels.PlacesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(
            this,
            PlacesViewModelFactory(PlacesRepositoryImp(PlacesService.instance))

        ).get(PlacesViewModel::class.java)
        viewModel.getData()

    }
}
