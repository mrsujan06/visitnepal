package com.zero.visitnepal.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zero.visitnepal.R
import com.zero.visitnepal.databinding.FragmentCityBinding
import com.zero.visitnepal.remote.PlacesService
import com.zero.visitnepal.repository.PlacesRepositoryImp
import com.zero.visitnepal.viewmodels.CityViewModel
import com.zero.visitnepal.viewmodels.CityViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class CityFragment : Fragment() {

    private lateinit var viewModel: CityViewModel
    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCityBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_city, container, false)

        viewModel = ViewModelProvider(
            this,
            CityViewModelFactory(PlacesRepositoryImp(PlacesService.instance))
        ).get(CityViewModel::class.java)

        cityAdapter = CityAdapter()
        binding.citiesRv.adapter = cityAdapter

        viewModel.citiesList.observe(viewLifecycleOwner, Observer {
            cityAdapter.setData(it)
        }
        )
        return binding.root
    }
}
