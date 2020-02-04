package com.zero.visitnepal.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zero.visitnepal.App
import com.zero.visitnepal.R
import com.zero.visitnepal.databinding.FragmentHomeBinding
import com.zero.visitnepal.model.PlacesResponse
import com.zero.visitnepal.repository.PlacesRepository
import com.zero.visitnepal.ui.home.adapter.HomePlacesAdapter
import com.zero.visitnepal.ui.home.adapter.HomeViewPagerAdapter
import com.zero.visitnepal.utils.ZoomOutPageTransformer
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    @Inject
    lateinit var placesRepository: PlacesRepository

    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var homeCityAdapter: HomePlacesAdapter
    private lateinit var homeAttractionAdapter: HomePlacesAdapter
    private lateinit var homeMountainAdapter: HomePlacesAdapter
    private lateinit var homeTempleAdapter: HomePlacesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        (activity?.applicationContext as App).appComponent.inject(this)
        setViewPager(binding)
        setAdapter(binding)
        setViewModel()
        return binding.root
    }

    private fun setViewPager(binding: FragmentHomeBinding) {
        binding.pager.adapter = HomeViewPagerAdapter()
        binding.indicator.setViewPager(binding.pager)
        binding.pager.setPageTransformer(ZoomOutPageTransformer())
    }

    private fun setAdapter(binding: FragmentHomeBinding) {
        homeCityAdapter = HomePlacesAdapter()
        homeAttractionAdapter = HomePlacesAdapter()
        homeMountainAdapter = HomePlacesAdapter()
        homeTempleAdapter = HomePlacesAdapter()

        binding.cities.setAdapter(homeCityAdapter)
        binding.attractions.setAdapter(homeAttractionAdapter)
        binding.mountains.setAdapter(homeMountainAdapter)
        binding.temples.setAdapter(homeTempleAdapter)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, HomeFragmentViewModelFactory(placesRepository)).get(
            HomeFragmentViewModel::class.java
        )
        viewModel.fetchPlacesData()
        observePlaces(viewModel.cityObservable, homeCityAdapter)
        observePlaces(viewModel.attractionObservable, homeAttractionAdapter)
        observePlaces(viewModel.mountainObservable, homeMountainAdapter)
        observePlaces(viewModel.templeObservable, homeTempleAdapter)
    }

    private fun observePlaces(
        liveData: LiveData<PlacesResponse>,
        placesAdapter: HomePlacesAdapter
    ) {
        liveData.observe(viewLifecycleOwner, Observer {
            placesAdapter.setData(it)
        })
    }
}
