package com.zero.visitnepal.ui.home


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
import com.zero.visitnepal.databinding.FragmentHomeBinding
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        (activity?.applicationContext as App).appComponent.inject(this)

        binding.pager.adapter =
            HomeViewPagerAdapter()
        binding.indicator.setViewPager(binding.pager)
        binding.pager.setPageTransformer(ZoomOutPageTransformer())

        //city adapter
        homeCityAdapter = HomePlacesAdapter()
        binding.homeCityListRv.adapter = homeCityAdapter

        //Attraction adapter
        homeAttractionAdapter = HomePlacesAdapter()
        binding.homeTopAttractionListRv.adapter = homeAttractionAdapter

        //Mountain adapter
        homeMountainAdapter = HomePlacesAdapter()
        binding.homeMountainListRv.adapter = homeMountainAdapter

        viewModel = ViewModelProvider(this, HomeFragmentViewModelFactory(placesRepository)).get(
            HomeFragmentViewModel::class.java
        )

        viewModel.getCitiesData()
        viewModel.getAttractionData()
        viewModel.getMountainsData()

        viewModel.placesList.observe(viewLifecycleOwner, Observer {
            homeCityAdapter.setData(it)
        })

        viewModel.attractionList.observe(viewLifecycleOwner, Observer {
            homeAttractionAdapter.setData(it)
        })

        viewModel.mountainList.observe(viewLifecycleOwner, Observer {
            homeMountainAdapter.setData(it)
        })

        return binding.root
    }
}
