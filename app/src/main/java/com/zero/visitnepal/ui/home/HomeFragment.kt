package com.zero.visitnepal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.zero.visitnepal.utils.ConnectionChecker
import com.zero.visitnepal.utils.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    @Inject
    lateinit var placesRepository: PlacesRepository

    @Inject
    lateinit var connectionChecker: ConnectionChecker

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
        onRetry(binding)
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
        viewModel = ViewModelProvider(
            this,
            HomeFragmentViewModelFactory(placesRepository, connectionChecker)
        ).get(
            HomeFragmentViewModel::class.java
        )
        viewModel.fetchPlacesData()
        observePlaces(viewModel.cityObservable, homeCityAdapter)
        observePlaces(viewModel.attractionObservable, homeAttractionAdapter)
        observePlaces(viewModel.mountainObservable, homeMountainAdapter)
        observePlaces(viewModel.templeObservable, homeTempleAdapter)

        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeFragmentViewModel.LoadingState.LOADING -> displayProgressbar()
                HomeFragmentViewModel.LoadingState.DONE -> displayPlaces()
                else -> displayConnectionError()
            }
        })

        cities.navigate(R.id.action_homeFragment_to_cityFragment)

    }

    private fun observePlaces(
        liveData: LiveData<PlacesResponse>,
        placesAdapter: HomePlacesAdapter
    ) {
        liveData.observe(viewLifecycleOwner, Observer {
            placesAdapter.setData(it)
        })
    }

    private fun onRetry(binding: FragmentHomeBinding) {
        binding.retryButton.setOnClickListener {
            viewModel.fetchPlacesData()
        }
    }

    private fun displayPlaces() {
        home_container.visibility = View.VISIBLE
        progressbar_container.visibility = View.GONE
        error_container.visibility = View.GONE
    }

    private fun displayProgressbar() {
        progressbar_container.visibility = View.VISIBLE
        home_container.visibility = View.GONE
        error_container.visibility = View.GONE
    }

    private fun displayConnectionError() {
        error_container.visibility = View.VISIBLE
        progressbar_container.visibility = View.GONE
        home_container.visibility = View.GONE
        Toast.makeText(context, R.string.network_error_message, Toast.LENGTH_SHORT).show()
    }
}
