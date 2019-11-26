package com.zero.visitnepal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zero.visitnepal.remote.PlacesService
import com.zero.visitnepal.repository.PlacesRepositoryImp
import com.zero.visitnepal.viewmodels.CityViewModel
import com.zero.visitnepal.viewmodels.CityViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            CityViewModelFactory(PlacesRepositoryImp(PlacesService.instance))
        ).get(CityViewModel::class.java)

    }
}
