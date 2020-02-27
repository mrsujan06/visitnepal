package com.zero.visitnepal.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zero.visitnepal.App
import com.zero.visitnepal.R
import com.zero.visitnepal.databinding.FragmentCityBinding
import com.zero.visitnepal.repository.PlacesRepository
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CityFragment : Fragment() {

    @Inject
    lateinit var placesRepository: PlacesRepository
    private lateinit var viewModel: CityViewModel
    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCityBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_city, container, false)
        (activity?.applicationContext as App).appComponent.inject(this)
        setViewModel()
        setAdapter(binding)
        return binding.root
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(
            this,
            CityViewModelFactory(placesRepository)
        ).get(CityViewModel::class.java)

        val token = arguments?.getString("token")
        viewModel.getData()
        token?.let { viewModel.getDataUsingToken(it) }
    }

    private fun setAdapter(binding: FragmentCityBinding) {
        cityAdapter = CityAdapter()
        binding.citiesRv.adapter = cityAdapter

        viewModel.citiesListObservable.observe(viewLifecycleOwner, Observer {
            cityAdapter.setData(it)
        })
    }

}
