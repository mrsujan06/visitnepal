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
import com.zero.visitnepal.model.Result
import com.zero.visitnepal.repository.PlacesRepository
import com.zero.visitnepal.ui.home.adapter.HomeParentAdapter
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
    private lateinit var homeAdapter: HomeParentAdapter

    private var placeList = mutableListOf<Result>()
    private var attractionList = mutableListOf<Result>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        (activity?.applicationContext as App).appComponent.inject(this)

        binding.homePager.adapter = HomeViewPagerAdapter()
        binding.homeIndicator.setViewPager(binding.homePager)
        binding.homePager.setPageTransformer(ZoomOutPageTransformer())

        viewModel = ViewModelProvider(
            this,
            HomeFragmentViewModelFactory(placesRepository)
        ).get(HomeFragmentViewModel::class.java)

        viewModel.getData()
        viewModel.getAttractionData()

        homeAdapter = HomeParentAdapter()
        binding.homeParentRv.adapter = homeAdapter

        viewModel.placesList.observe(viewLifecycleOwner, Observer {
            placeList.addAll(it.results)
        })

        viewModel.attractionList.observe(viewLifecycleOwner, Observer{
             attractionList.addAll(it.results)
        })

        homeAdapter.setData(placeList, attractionList)
        return binding.root
    }
}
